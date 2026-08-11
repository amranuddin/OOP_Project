package com.mycompany.swing;

class Person extends HospitalService {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    void showDetails() { System.out.println("Name: " + name); }
}