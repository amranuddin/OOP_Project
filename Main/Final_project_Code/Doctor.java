package com.mycompany.swing;

class Doctor extends Person {
    private String specialization;
    private String availableTime;

    public Doctor(String name, String specialization, String availableTime) {
        super(name);
        this.specialization = specialization;
        this.availableTime = availableTime;
    }

    public String getSpecialization() { return specialization; }
    public String getAvailableTime() { return availableTime; }

    @Override
    void showDetails() {
        System.out.println("Doctor : " + name);
        System.out.println("Specialization : " + specialization);
        System.out.println("Available Time : " + availableTime);
    }
}