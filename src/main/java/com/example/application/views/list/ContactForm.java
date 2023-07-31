package com.example.application.views.list;

import com.example.application.data.model.Company;
import com.example.application.data.model.Contact;
import com.example.application.data.model.Status;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class ContactForm extends FormLayout {
    private final Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    ComboBox<Status> status;
    ComboBox<Company> company;

    private final Button saveButton;

    public ContactForm(List<Company> companies, List<Status> statuses, ContactFormActionsHandler actionsHandler) {
        status = new ComboBox<>("Status", statuses);
        company = new ComboBox<>("Company", companies);

        status.setItemLabelGenerator(Status::getName);
        company.setItemLabelGenerator(Company::getName);

        HorizontalLayout buttonsLayout = new HorizontalLayout();

        saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickListener(e -> {
            if (binder.getBean() != null) {
                actionsHandler.saveContact(binder.getBean());
                saveButton.setEnabled(false);
            }
        });

        Button deleteButton = new Button("Delete");
        Button canceButton = new Button("Cancel");
        canceButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonsLayout.add(saveButton, deleteButton, canceButton);
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
        binder.addValueChangeListener(e -> saveButton.setEnabled(true));
    }

    public void setContact(Contact contact) {
        binder.setBean(contact);
        saveButton.setEnabled(false);

    }

    public interface ContactFormActionsHandler {
        public void saveContact(Contact contact);
        public void deleteContact(Contact contact);
    }
}
