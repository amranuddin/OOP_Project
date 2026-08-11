package com.mycompany.swing;

abstract class Bill {
    abstract double calculateBill();
}

class CabinBill extends Bill {
    private int days;
    private double ratePerDay = 4500.0;

    public CabinBill(int days) {
        this.days = days;
    }

    @Override
    double calculateBill() { return days * ratePerDay; }
}

class GeneralBill extends Bill {
    private int days;
    private double ratePerDay = 1200.0;

    public GeneralBill(int days) {
        this.days = days;
    }

    @Override
    double calculateBill() { return days * ratePerDay; }
}

class Billing extends Bill {
    private double doctorFee;
    private double medicineFee;
    private int cabinDays;
    private double cabinRate;
    private int wardDays;
    private double wardRate;
    private double labTest;

    public Billing(double doctorFee, double medicineFee, int cabinDays, double cabinRate, int wardDays, double wardRate, double labTest) {
        this.doctorFee = doctorFee;
        this.medicineFee = medicineFee;
        this.cabinDays = cabinDays;
        this.cabinRate = cabinRate;
        this.wardDays = wardDays;
        this.wardRate = wardRate;
        this.labTest = labTest;
    }

    @Override
    double calculateBill() {
        return doctorFee + medicineFee + (cabinDays * cabinRate) + (wardDays * wardRate) + labTest;
    }
}