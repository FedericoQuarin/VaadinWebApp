package com.example.application.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private List<Contact> empleados = new ArrayList<>();

    public Company(String name, List<Contact> empleados) {
        this.name = name;
        this.empleados = empleados;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Contact> empleados) {
        this.empleados = empleados;
    }
}

