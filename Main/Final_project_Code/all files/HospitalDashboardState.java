package com.mycompany.swing;

import java.util.ArrayList;

class HospitalDashboardState {
    private final ArrayList<Bed> bedList = new ArrayList<>();

    public HospitalDashboardState() {
        bedList.add(new Bed("C-101", "Cabin", "VIP Single", true, true, true));
        bedList.add(new Bed("C-102", "Cabin", "Deluxe AC", false, true, true));
        bedList.add(new Bed("C-103", "Cabin", "Double AC", false, false, true));
        bedList.add(new Bed("C-104", "Cabin", "Non-AC Cabin", false, false, false));
        bedList.add(new Bed("C-105", "Cabin", "VIP Single", true, true, true));
        bedList.add(new Bed("C-106", "Cabin", "Deluxe AC", false, true, true));
        bedList.add(new Bed("C-107", "Cabin", "Double AC", false, false, true));
        bedList.add(new Bed("C-108", "Cabin", "Non-AC Cabin", false, false, false));

        bedList.add(new Bed("W-201", "Ward", "Male Ward", false, true, true));
        bedList.add(new Bed("W-202", "Ward", "Female Ward", false, false, true));
        bedList.add(new Bed("W-203", "Ward", "Pediatric (Child) Ward", false, true, true));
        bedList.add(new Bed("W-204", "Ward", "Isolation Ward", true, true, true));
        bedList.add(new Bed("W-205", "Ward", "Male Ward", false, true, true));
        bedList.add(new Bed("W-206", "Ward", "Female Ward", false, false, true));
        bedList.add(new Bed("W-207", "Ward", "Pediatric (Child) Ward", false, true, true));
        bedList.add(new Bed("W-208", "Ward", "Isolation Ward", true, true, true));

        bedList.get(0).assignPatient("P-101 (Karim Ullah)", "2026-07-24", "Dr. Rahman / Nurse Sarah");
    }

    public ArrayList<Bed> getBedList() 
    { 
        return bedList; 
    }

    public int getTotalCabins() {
        return (int) bedList.stream().filter(b -> b.getMainCategory().equals("Cabin")).count();
    }

    public int getOccupiedCabins() {
        return (int) bedList.stream().filter(b -> b.getMainCategory().equals("Cabin") && b.isOccupied()).count();
    }

    public int getAvailableCabins() { return getTotalCabins() - getOccupiedCabins(); }

    public int getTotalBeds() {
        return (int) bedList.stream().filter(b -> b.getMainCategory().equals("Ward")).count();
    }

    public int getOccupiedBeds() {
        return (int) bedList.stream().filter(b -> b.getMainCategory().equals("Ward") && b.isOccupied()).count();
    }

    public int getAvailableBeds() { return getTotalBeds() - getOccupiedBeds(); }
}