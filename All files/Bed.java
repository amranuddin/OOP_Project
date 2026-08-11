package com.mycompany.swing;

import java.util.ArrayList;

class Bed {
    private String id;
    private String mainCategory; 
    private String subCategory;   
    private boolean isOccupied;
    private String patientIdName; 
    private String admissionDate;
    private String assignedStaff;  
    private boolean hasVentilator;
    private boolean hasMonitor;
    private boolean hasOxygen;

    public Bed(String id, String mainCategory, String subCategory, boolean hasVentilator, boolean hasMonitor, boolean hasOxygen) {
        this.id = id;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.isOccupied = false;
        this.patientIdName = "None";
        this.admissionDate = "N/A";
        this.assignedStaff = "None";
        this.hasVentilator = hasVentilator;
        this.hasMonitor = hasMonitor;
        this.hasOxygen = hasOxygen;
    }

    public String getId() { return id; }
    public String getMainCategory() { return mainCategory; }
    public String getSubCategory() { return subCategory; }
    public boolean isOccupied() { return isOccupied; }
    public String getPatientIdName() { return patientIdName; }
    public String getAdmissionDate() { return admissionDate; }
    public String getAssignedStaff() { return assignedStaff; }
    public boolean hasVentilator() { return hasVentilator; }
    public boolean hasMonitor() { return hasMonitor; }
    public boolean hasOxygen() { return hasOxygen; }

    public void assignPatient(String patientIdName, String admissionDate, String assignedStaff) {
        this.patientIdName = patientIdName;
        this.admissionDate = admissionDate;
        this.assignedStaff = assignedStaff;
        this.isOccupied = true;
    }

    public void releasePatient() {
        this.patientIdName = "None";
        this.admissionDate = "N/A";
        this.assignedStaff = "None";
        this.isOccupied = false;
    }

    public String getSupportSummary() {
        ArrayList<String> supports = new ArrayList<>();
        if (hasVentilator) supports.add("Ventilator");
        if (hasMonitor) supports.add("Monitor");
        if (hasOxygen) supports.add("Oxygen");
        return supports.isEmpty() ? "Basic Bed" : String.join(", ", supports);
    }
}