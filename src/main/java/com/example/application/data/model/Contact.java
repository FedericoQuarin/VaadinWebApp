package com.example.application.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Contact extends AbstractEntity {
    @NotEmpty(message = "Must not be empty")
    private String firstName = "";

    @NotEmpty(message = "Must not be empty")
    private String lastName = "";

    @NotNull
    @Email(message = "Not a valid email address")
    private String email = "";

    @NotNull
    @ManyToOne
    private Status status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties({"employees"})
    private Company company;

    public Contact(String firstName, String lastName, String email, Status status, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.company = company;
    }

    public Contact(Contact contact) {
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.email = contact.getEmail();
        this.status = contact.getStatus();
        this.company = contact.getCompany();
        super.setId(contact.getId());
    }

    public Contact() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
