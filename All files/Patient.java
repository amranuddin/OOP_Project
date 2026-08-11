package com.mycompany.swing;

class Patient extends Person {
    protected int age;
    protected String disease;

    public Patient(String name, int age, String disease) {
        super(name);
        this.age = age;
        this.disease = disease;
    }

    public int getAge() { return age; }
    public String getDisease() { return disease; }

    @Override
    void showDetails() {
        System.out.println("Patient Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Disease : " + disease);
    }
}