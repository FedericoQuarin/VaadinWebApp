package com.example.application.views.list;

import com.example.application.model.Company;
import com.example.application.model.Status;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ContactForm extends FormLayout {
    public ContactForm(List<Company> companies, List<Status> statuses) {
        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        TextField emailField = new TextField("Email");
        ComboBox<Status> statusComboBox = new ComboBox<>("Status", statuses);
        ComboBox<Company> companyComboBox = new ComboBox<>("Company", companies);

        statusComboBox.setItemLabelGenerator(Status::getName);
        companyComboBox.setItemLabelGenerator(Company::getName);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        Button saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button deleteButton = new Button("Delete");
        Button canceButton = new Button("Cancel");
        canceButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonsLayout.add(saveButton, deleteButton, canceButton);
        buttonsLayout.setWidthFull();

        add(
                firstNameField,
                lastNameField,
                emailField,
                statusComboBox,
                companyComboBox,
                buttonsLayout);

        setResponsiveSteps(new ResponsiveStep("0", 1));
    }
}
