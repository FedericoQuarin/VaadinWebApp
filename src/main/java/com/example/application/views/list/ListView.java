package com.example.application.views.list;

import com.example.application.data.model.AbstractEntity;
import com.example.application.data.model.Company;
import com.example.application.data.model.Contact;
import com.example.application.data.model.Status;
import com.example.application.data.repos.ContactRepository;
import com.example.application.data.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Contacts")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {
    private HorizontalLayout toolbar;
    private Grid<Contact> grid;
    private ContactForm form;

    private TextField filterField;

    private final CrmService service;

    public ListView(CrmService service) {
        this.service = service;

        configureToobar();
        add(toolbar, configureContent());

        updateList();

        grid.addSelectionListener(e -> {
            if (e.isFromClient()) {
                form.setContact(grid.asSingleSelect().getValue());
            }
        });
    }

    private void configureToobar() {
        toolbar = new HorizontalLayout();

        filterField = new TextField();
        filterField.setValueChangeMode(ValueChangeMode.LAZY);
        filterField.setPlaceholder("Filter by name...");
        filterField.setClearButtonVisible(true);
        filterField.addValueChangeListener(e -> {
            if (e.isFromClient()) updateList();
        });

        Button addContactButton = new Button("Add Contact");

        toolbar.add(filterField, addContactButton);
    }

    private Component configureContent() {
        HorizontalLayout layout = new HorizontalLayout();

        configureGrid();
        configureForm();

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
        form = new ContactForm(
                service.findAllCompanies(),
                service.findAllStatus(),
                new ContactForm.ContactFormActionsHandler() {
                    @Override
                    public void saveContact(Contact contact) {
                        service.saveContact(contact);
                        updateList();
                        Notification notification = Notification.show(
                                "Contact saved succesfully!",
                                5000,
                                Notification.Position.BOTTOM_START);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.open();
                    }

                    @Override
                    public void deleteContact(Contact contact) {
                        service.deleteContact(contact);
                        updateList();
                    }
                });
        form.setWidth("30em");
    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filterField.getValue()));
    }
}
