

package com.mycompany.billing;

public class Billing {

    private double doctorFee;
    private double medicineFee;
    private double cabinCharge;
    private double Labtest;
    public Billing(double doctorFee, double medicineFee, double cabinCharge,double Labtest) {
        this.doctorFee = doctorFee;
        this.medicineFee = medicineFee;
        this.cabinCharge = cabinCharge;
        this.Labtest=Labtest;
    }

    public double calculateTotal() {
        return cabinCharge + Labtest+doctorFee + medicineFee;
    }
}
    

