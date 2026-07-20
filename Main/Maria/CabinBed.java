
package com.mycompany.cabinbed;
public class CabinBed {

    private  String patientName;
    private  String cabinNo;
    private String bedNo;
    private  String status;

    public CabinBed(String patientName, String cabinNo, String bedNo, String status) {
        this.patientName = patientName;
        this.cabinNo = cabinNo;
        this.bedNo = bedNo;
        this.status = status;
    }

    public String assignBed() {
        if (status.equals("Available")) {
            return patientName + " assigned to " + cabinNo + " (" + bedNo + ")";
        } else {
            return "Cabin is occupied.";
        }
    }

    public static void main(String[] args) {
    }
    }

