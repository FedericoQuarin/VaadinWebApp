package com.example.application.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Company extends AbstractEntity {
    @NotEmpty
    private String name = "";

    @OneToMany(mappedBy = "company")
    @Nullable
    private List<Contact> employees;

    public Company(String name, List<Contact> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Contact> employees) {
        this.employees = employees;
    }

}

