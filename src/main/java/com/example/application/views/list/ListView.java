package com.example.application.views.list;

import com.example.application.model.Person;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout {
    public ListView() {
        TextField filterField = new TextField();
        Button addContactButton = new Button("Add Contact");

        HorizontalLayout hl = new HorizontalLayout();

        hl.add(filterField, addContactButton);

        Grid<Person> grid = new Grid<>(Person.class);


        add(hl, grid);
    }

}
