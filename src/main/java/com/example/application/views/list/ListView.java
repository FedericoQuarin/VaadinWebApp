package com.example.application.views.list;

import com.example.application.model.Contact;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contacts")
@Route(value = "", layout = MainLayout.class)
public class ListView extends VerticalLayout {
    private Grid<Contact> grid;

    public ListView() {
        TextField filterField = new TextField();
        Button addContactButton = new Button("Add Contact");

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(filterField, addContactButton);

        configureGrid();

        add(hl, grid);
    }

    private void configureGrid() {
        grid = new Grid<>(Contact.class, false);
        grid.addColumn(Contact::getFirstName).setHeader("First Name");
        grid.addColumn(Contact::getLastName).setHeader("Last Name");
        grid.addColumn(Contact::getEmail).setHeader("Email");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
    }
}
