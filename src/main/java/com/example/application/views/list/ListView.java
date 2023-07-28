package com.example.application.views.list;

import com.example.application.model.Company;
import com.example.application.model.Contact;
import com.example.application.model.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("Contacts")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {
    private HorizontalLayout toolbar;
    private Grid<Contact> grid;
    private FormLayout form;

    private static List<Company> companies = Arrays.asList(
            new Company("Flowing Code SA", new ArrayList<>()),
            new Company("Playcom SA", new ArrayList<>()));
    private static List<Status> statuses = Arrays.asList(
            new Status("Contacted"),
            new Status("Email sent")
    );

    public ListView() {
        configureToobar();

        add(toolbar, configureContent());
    }

    private void configureToobar() {
        toolbar = new HorizontalLayout();

        TextField filterField = new TextField();
        filterField.setValueChangeMode(ValueChangeMode.LAZY);
        filterField.setPlaceholder("Filter by name...");
        filterField.setClearButtonVisible(true);

        Button addContactButton = new Button("Add Contact");

        toolbar.add(filterField, addContactButton);
    }

    private Component configureContent() {
        HorizontalLayout layout = new HorizontalLayout();

        configureGrid();
        configureForm();

        grid.setItems(contactList());

        layout.add(grid, form);
        layout.setFlexGrow(2, grid);
        layout.setFlexGrow(1, form);
        layout.setSizeFull();

        return layout;
    }

    private void configureGrid() {
        grid = new Grid<>(Contact.class, false);
        grid.addClassName("contact-grid");
        grid.setWidthFull();
        grid.addColumn(Contact::getFirstName).setHeader("First Name");
        grid.addColumn(Contact::getLastName).setHeader("Last Name");
        grid.addColumn(Contact::getEmail).setHeader("Email");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void configureForm() {
        form = new ContactForm(companies, statuses);
        form.setWidth("30em");
    }

    public static List<Contact> contactList() {
        List<Contact> contactList = new ArrayList<>();

        Contact contact1 = new Contact(
                "Valentin",
                "Reynoso",
                "valenreynoso@gmail.com",
                statuses.get(0),
                companies.get(0));
        companies.get(0).getEmployees().add(contact1);
        contactList.add(contact1);

        Contact contact2 = new Contact(
                "Federico",
                "Quarin",
                "federicoquarin@gmail.com",
                statuses.get(1),
                companies.get(0));
        companies.get(0).getEmployees().add(contact2);
        contactList.add(contact2);

        return contactList;
    }


}
