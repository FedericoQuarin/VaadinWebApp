package com.example.application.views.list;

import com.example.application.data.model.Company;
import com.example.application.data.model.Contact;
import com.example.application.data.model.Status;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.List;

public class ContactForm extends FormLayout {
    private final Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);
    private Contact contact;

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    ComboBox<Status> status;
    ComboBox<Company> company;

    private final Button saveButton;
    private final Button deleteButton;
    private final Button cancelButton;

    public ContactForm(List<Company> companies, List<Status> statuses, ContactFormActionsHandler actionsHandler) {
        status = new ComboBox<>("Status", statuses);
        company = new ComboBox<>("Company", companies);

        status.setItemLabelGenerator(Status::getName);
        company.setItemLabelGenerator(Company::getName);


        HorizontalLayout buttonsLayout = new HorizontalLayout();

        saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.setEnabled(false);

        deleteButton = new Button("Delete");
        deleteButton.addClickListener(e -> {
            actionsHandler.deleteContact(contact);
        });

        cancelButton = new Button("Cancel");
        cancelButton.setEnabled(false);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancelButton.addClickListener(e -> binder.readBean(contact));

        saveButton.addClickListener(e -> {
            try {
                binder.writeBean(contact);
                actionsHandler.saveContact(contact);
                saveButton.setEnabled(false);
                cancelButton.setEnabled(false);
            } catch (ValidationException exc) {
                exc.printStackTrace();
            }
        });

        buttonsLayout.add(saveButton, deleteButton, cancelButton);
        buttonsLayout.setWidthFull();


        add(
                firstName,
                lastName,
                email,
                status,
                company,
                buttonsLayout);

        setResponsiveSteps(new ResponsiveStep("0", 1));

        binder.bindInstanceFields(this);
        binder.addValueChangeListener(e -> {
            saveButton.setEnabled(true);
            cancelButton.setEnabled(true);
        });
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        binder.readBean(contact);
        setEnabled(true);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }

    public interface ContactFormActionsHandler {
        void saveContact(Contact contact);
        void deleteContact(Contact contact);
    }
}
