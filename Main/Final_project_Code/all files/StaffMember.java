package com.mycompany.swing;

class StaffMember {
    private String name;
    private String role;
    private String shift;
    private String status;

    public StaffMember(String name, String role, String shift, String status) {
        this.name = name;
        this.role = role;
        this.shift = shift;
        this.status = status;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public String getShift() { return shift; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}