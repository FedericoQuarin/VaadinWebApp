package com.example.application.views.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.example.application.data.model.Company;
import com.example.application.data.model.Contact;
import com.example.application.data.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactFormTest {
    private List<Company> companies;
    private List<Status> statuses;
    private Contact marcUsher;
    private Company company1;
    private Company company2;
    private Status status1;
    private Status status2;

    @BeforeEach  
    public void setupData() {
        companies = new ArrayList<>();
        company1 = new Company();
        company1.setName("Vaadin Ltd");
        company2 = new Company();
        company2.setName("IT Mill");
        companies.add(company1);
        companies.add(company2);

        statuses = new ArrayList<>();
        status1 = new Status();
        status1.setName("Status 1");
        status2 = new Status();
        status2.setName("Status 2");
        statuses.add(status1);
        statuses.add(status2);

        marcUsher = new Contact();
        marcUsher.setFirstName("Marc");
        marcUsher.setLastName("Usher");
        marcUsher.setEmail("marc@usher.com");
        marcUsher.setStatus(status1);
        marcUsher.setCompany(company2);
    }

    @Test
    public void formFieldsPopulated() {
        ContactForm form = new ContactForm(companies, statuses, null);
        form.setContact(marcUsher);

        Assertions.assertEquals("Marc", form.firstName.getValue());
        Assertions.assertEquals("Usher", form.lastName.getValue());
        Assertions.assertEquals("marc@usher.com", form.email.getValue());
        Assertions.assertEquals(company2, form.company.getValue());
        Assertions.assertEquals(status1, form.status.getValue());
    }

    @Test
    public void saveEventHasCorrectValues() {
        AtomicReference<Contact> saveContact = new AtomicReference<>(null);
        ContactForm form = new ContactForm(companies, statuses, new ContactForm.ContactFormActionsHandler() {
            @Override
            public void saveContact(Contact contact) {
                saveContact.set(contact);
            }

            @Override
            public void deleteContact(Contact contact) {
                // noop
            }

            @Override
            public void cancelAction() {
                // noop
            }
        });


        form.setEmptyContact();

        form.firstName.setValue("Federico");
        form.lastName.setValue("Quarin");
        form.email.setValue("federico@gmail.com");
        form.company.setValue(company1);
        form.status.setValue(status2);

        form.saveButton.click();
        Contact contact = saveContact.get();

        Assertions.assertEquals(contact.getFirstName(), "Federico");
        Assertions.assertEquals(contact.getLastName(), "Quarin");
        Assertions.assertEquals(contact.getEmail(), "federico@gmail.com");
        Assertions.assertEquals(contact.getCompany(), company1);
        Assertions.assertEquals(contact.getStatus(), status2);
    }

}