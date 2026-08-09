package com.mycompany.swing;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

abstract class HospitalService {
    abstract void showDetails();
}

class Person extends HospitalService {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    void showDetails() { System.out.println("Name: " + name); }
}

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

class Appointment {
    private String patientName;
    private String doctorName;
    private String date;
    private String time;
    private String status;

    public Appointment(String patientName, String doctorName, String date, String time, String status) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

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

class MixedGradientPanel extends JPanel {
    private Color colorStart;
    private Color colorMid;
    private Color colorEnd;

    public MixedGradientPanel(Color start, Color mid, Color end) {
        this.colorStart = start;
        this.colorMid = mid;
        this.colorEnd = end;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        LinearGradientPaint lgp = new LinearGradientPaint(
                0, 0, width, height,
                new float[]{0.0f, 0.5f, 1.0f},
                new Color[]{colorStart, colorMid, colorEnd}
        );

        g2.setPaint(lgp);
        g2.fillRect(0, 0, width, height);
    }
}

class DashboardChartPanel extends JPanel {

    private final HospitalDashboardState state;

    private static final Color COLOR_BLUE_LIGHT = new Color(37, 99, 235);
    private static final Color COLOR_VIOLET_LIGHT = new Color(147, 51, 234);
    private static final Color COLOR_CYAN_LIGHT = new Color(13, 148, 136);
    private static final Color COLOR_TEXT = new Color(51, 65, 85);

    public DashboardChartPanel(HospitalDashboardState state) {
        this.state = state;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        int padding = 40;
        int graphHeight = height - (2 * padding) - 30;

        g2.setColor(new Color(203, 213, 225));
        g2.drawLine(padding, height - padding - 20, width - padding, height - padding - 20);
        g2.drawLine(padding, padding, padding, height - padding - 20);

        int maxCapacity = Math.max(state.getTotalCabins(), state.getTotalBeds());
        if (maxCapacity == 0) maxCapacity = 1;

        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2.setColor(COLOR_TEXT);

        drawBarGroup(g2, padding + 40, height - padding - 20, graphHeight, maxCapacity, 
                     "Cabins", state.getTotalCabins(), state.getOccupiedCabins(), state.getAvailableCabins());

        drawBarGroup(g2, padding + 220, height - padding - 20, graphHeight, maxCapacity, 
                     "Ward Beds", state.getTotalBeds(), state.getOccupiedBeds(), state.getAvailableBeds());

        drawLegend(g2, width - 180, 20);
    }

    private void drawBarGroup(Graphics2D g2, int startX, int baseY, int graphHeight, int maxVal, 
                              String title, int total, int booked, int avail) {
        int barWidth = 28;

        int totalBarH = (int) (((double) total / maxVal) * (graphHeight - 20));
        int bookedBarH = (int) (((double) booked / maxVal) * (graphHeight - 20));
        int availBarH = (int) (((double) avail / maxVal) * (graphHeight - 20));

        g2.setColor(COLOR_BLUE_LIGHT);
        g2.fillRect(startX, baseY - totalBarH, barWidth, totalBarH);
        g2.drawString(String.valueOf(total), startX + 8, baseY - totalBarH - 5);

        g2.setColor(COLOR_VIOLET_LIGHT);
        g2.fillRect(startX + barWidth + 6, baseY - bookedBarH, barWidth, bookedBarH);
        g2.drawString(String.valueOf(booked), startX + barWidth + 14, baseY - bookedBarH - 5);

        g2.setColor(COLOR_CYAN_LIGHT);
        g2.fillRect(startX + (barWidth * 2) + 12, baseY - availBarH, barWidth, availBarH);
        g2.drawString(String.valueOf(avail), startX + (barWidth * 2) + 20, baseY - availBarH - 5);

        g2.setColor(COLOR_TEXT);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        g2.drawString(title, startX + 15, baseY + 20);
    }

    private void drawLegend(Graphics2D g2, int x, int y) {
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        g2.setColor(COLOR_BLUE_LIGHT);
        g2.fillRect(x, y, 12, 12);
        g2.setColor(COLOR_TEXT);
        g2.drawString("Total", x + 18, y + 10);

        g2.setColor(COLOR_VIOLET_LIGHT);
        g2.fillRect(x, y + 18, 12, 12);
        g2.setColor(COLOR_TEXT);
        g2.drawString("Booked", x + 18, y + 28);

        g2.setColor(COLOR_CYAN_LIGHT);
        g2.fillRect(x, y + 36, 12, 12);
        g2.setColor(COLOR_TEXT);
        g2.drawString("Available", x + 18, y + 46);
    }
}

class KpiCardPanel extends JPanel {

    public KpiCardPanel(String title, JLabel valueLabel, Color accentColor, Color bgColor) {
        setLayout(new BorderLayout(10, 10));
        setBackground(bgColor);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accentColor, 1),
                " " + title + " ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                accentColor
        ));

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        valueLabel.setForeground(accentColor);

        add(valueLabel, BorderLayout.CENTER);
    }
}

public class Swing extends JFrame {

    private static final Color COLOR_PURE_WHITE = new Color(255, 255, 255);
    private static final Color COLOR_CARD_WHITE = new Color(255, 255, 255);
    private static final Color COLOR_BG_OVERVIEW = new Color(0xF0, 0xF7, 0xFF);     
    private static final Color COLOR_BG_PATIENT_HUB = new Color(0xE8, 0xF8, 0xF5);  
    private static final Color COLOR_BG_DOCTOR_REG = new Color(0xF3, 0xE8, 0xFF);   
    private static final Color COLOR_BG_APPOINTMENTS = new Color(0xFF, 0xF7, 0xED); 
    private static final Color COLOR_BG_STAFF = new Color(238, 242, 255);
    private static final Color COLOR_CARD_STAFF = new Color(248, 250, 255);
    private static final Color COLOR_STAFF = new Color(79, 70, 229);
    private static final Color COLOR_BG_BILLING = new Color(0xF0, 0xF4, 0xF8);     
    private static final Color COLOR_CARD_OVERVIEW = new Color(0xFF, 0xFF, 0xFF);
    private static final Color COLOR_CARD_PATIENT_HUB = new Color(0xF0, 0xFD, 0xFA);
    private static final Color COLOR_CARD_DOCTOR_REG = new Color(0xFA, 0xF5, 0xFF);
    private static final Color COLOR_CARD_APPOINTMENTS = new Color(0xFF, 0xFB, 0xEB);
    private static final Color COLOR_CARD_BILLING = new Color(0xF8, 0xFA, 0xFC); 
    private static final Color COLOR_INPUT_BG = new Color(255, 255, 255);
    private static final Color COLOR_BORDER = new Color(203, 213, 225);
    private static final Color COLOR_BG_WARD_CABIN = new Color(224, 242, 241);
    private static final Color COLOR_CARD_TEAL = new Color(240, 250, 249);
    private static final Color COLOR_PRIMARY_BLUE = new Color(37, 99, 235);      
    private static final Color COLOR_VIOLET = new Color(124, 58, 237);          
    private static final Color COLOR_CYAN = new Color(13, 148, 136);            
    private static final Color COLOR_ORANGE = new Color(217, 119, 6);            
    private static final Color COLOR_SLATE = new Color(71, 85, 105);            
    private static final Color COLOR_RED = new Color(225, 29, 72);              
    private static final Color COLOR_GREEN = new Color(34, 197, 94);
    private static final Color COLOR_TEXT_DARK = new Color(30, 41, 59);
    private static final Color BILL_BLUE =new Color(37, 99, 235);
    private static final Color BILL_BLUE_LIGHT =new Color(239, 246, 255);
    private static final Color BILL_GREEN =new Color(34, 197, 94);
    private static final Color BILL_GREEN_LIGHT =new Color(240, 253, 244);
    private static final Color BILL_PURPLE =new Color(139, 92, 246);
    private static final Color BILL_PURPLE_LIGHT =new Color(250, 245, 255);

    private static final Color BILL_ORANGE =new Color(249, 115, 22);

    private static final Color BILL_ORANGE_LIGHT =new Color(255, 247, 237);

private static final Color BILL_PINK =
        new Color(236, 72, 153);

private static final Color BILL_PINK_LIGHT =
        new Color(253, 242, 248);

private static final Color BILL_CYAN =
        new Color(6, 182, 212);

private static final Color BILL_CYAN_LIGHT =
        new Color(236, 254, 255);

private static final Color BILL_BG =
        new Color(245, 249, 255);
    private final ArrayList<Patient> patientList = new ArrayList<>();
    private final ArrayList<Doctor> doctorList = new ArrayList<>();
    private final ArrayList<Appointment> appointmentList = new ArrayList<>();
    private final ArrayList<StaffMember> staffList = new ArrayList<>();

    private final HospitalDashboardState dashboardState = new HospitalDashboardState();

    private final JComboBox<String> appPatientCombo = new JComboBox<>();
    private final JComboBox<String> appDoctorCombo = new JComboBox<>();
    private final JComboBox<String> billPatientCombo = new JComboBox<>();

    private final JLabel totalPatientsVal = new JLabel("0", SwingConstants.CENTER);
    private final JLabel totalDoctorsVal = new JLabel("0", SwingConstants.CENTER);
    private final JLabel cabinStatusVal = new JLabel("", SwingConstants.CENTER);
    private final JLabel bedStatusVal = new JLabel("", SwingConstants.CENTER);

    private DefaultTableModel patientTableModel;
    private DefaultTableModel doctorTableModel;
    private DefaultTableModel appointmentTableModel;
    private DefaultTableModel staffTableModel;
    private DefaultTableModel emergencyTableModel;
    private DefaultTableModel trackerTableModel;

    private final JLabel doctorScheduleDisplay = new JLabel("Available: Select Doctor");

    private DashboardChartPanel chartPanel;
    private JTabbedPane tabbedPane;
    private JTextArea noticeDisplayArea;

    public Swing() {
        setTitle("CarePulse - Modern Hospital Management System");
        setSize(1250, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color bgStart = new Color(241, 245, 249);
        Color bgMid = new Color(224, 242, 254); 
        Color bgEnd = new Color(243, 232, 255); 

        MixedGradientPanel mainContainer = new MixedGradientPanel(bgStart, bgMid, bgEnd);
        setContentPane(mainContainer);

        staffList.add(new StaffMember("Dr. Rahman", "Senior Specialist", "Morning (8AM-4PM)", "On-Duty"));
        staffList.add(new StaffMember("Nurse Sarah", "ICU Staff Nurse", "Morning (8AM-4PM)", "On-Duty"));
        staffList.add(new StaffMember("Kalam Hossain", "Ward Assistant", "Evening (4PM-12AM)", "On-Call"));

        updateKpiLabels();

        UIManager.put("TabbedPane.selected", COLOR_PRIMARY_BLUE);
        UIManager.put("TabbedPane.contentAreaColor", new Color(0, 0, 0, 0));
        UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0));

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabbedPane.setBackground(COLOR_PURE_WHITE);
        tabbedPane.setForeground(COLOR_TEXT_DARK);

        tabbedPane.addTab(" Dashboard Overview ", createOverviewPanel());
        tabbedPane.addTab(" Patient Registration & Directory ", createCombinedPatientPanel());
        tabbedPane.addTab(" Register Doctor & Directory ", createDoctorRegistrationPanel());
        tabbedPane.addTab(" Appointments & Queue ", createAppointmentPanel());
        tabbedPane.addTab(" Staff & Shift Roster ", createStaffShiftPanel());
        tabbedPane.addTab(" Ward & Cabin Tracker ", createTrackerPanel());
        tabbedPane.addTab(" Billing Terminal ", createBillingPanel());

        mainContainer.add(tabbedPane, BorderLayout.CENTER);
    }

    private void updateKpiLabels() {
        cabinStatusVal.setText("Booked: " + dashboardState.getOccupiedCabins() + " | Avail: " + dashboardState.getAvailableCabins());
        bedStatusVal.setText("Booked: " + dashboardState.getOccupiedBeds() + " | Avail: " + dashboardState.getAvailableBeds());
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isRollover()) {
                    g2.setColor(bgColor.darker());
                } else {
                    g2.setColor(bgColor);
                }
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                
                super.paintComponent(g);
                g2.dispose();
            }
        };
        
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setForeground(fgColor);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        return btn;
    }

    private JTextField createStyledTextField(String defaultText, Color accentColor) {
        JTextField field = new JTextField(defaultText);
        field.setBackground(COLOR_INPUT_BG);
        field.setForeground(COLOR_TEXT_DARK);
        field.setCaretColor(accentColor);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return field;
    }

    private <T> JComboBox<T> createStyledComboBox(JComboBox<T> combo) {
        combo.setBackground(COLOR_INPUT_BG);
        combo.setForeground(COLOR_TEXT_DARK);
        combo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return combo;
    }

    private TitledBorder createCustomTitledBorder(String title, Color accentColor) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accentColor, 1),
                " " + title + " ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                accentColor
        );
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(COLOR_TEXT_DARK);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return label;
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setOpaque(true);
        panel.setBackground(COLOR_BG_OVERVIEW);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 12, 12));
        cardsPanel.setOpaque(false);
        cardsPanel.add(new KpiCardPanel("Registered Patients", totalPatientsVal, COLOR_CYAN, COLOR_CARD_OVERVIEW));
        cardsPanel.add(new KpiCardPanel("Registered Doctors", totalDoctorsVal, COLOR_VIOLET, COLOR_CARD_OVERVIEW));
        cardsPanel.add(new KpiCardPanel("Cabin Availability", cabinStatusVal, COLOR_SLATE, COLOR_CARD_OVERVIEW));
        cardsPanel.add(new KpiCardPanel("Ward Bed Availability", bedStatusVal, COLOR_PRIMARY_BLUE, COLOR_CARD_OVERVIEW));

        panel.add(cardsPanel, BorderLayout.NORTH);

        JPanel centerGrid = new JPanel(new GridLayout(1, 2, 12, 12));
        centerGrid.setOpaque(false);

        JPanel chartCard = new JPanel(new BorderLayout());
        chartCard.setBackground(COLOR_CARD_OVERVIEW);
        chartCard.setBorder(createCustomTitledBorder("Real-Time Capacity Visual Analytics", COLOR_PRIMARY_BLUE));
        chartPanel = new DashboardChartPanel(dashboardState);
        chartCard.add(chartPanel, BorderLayout.CENTER);

        JPanel rightColumn = new JPanel(new BorderLayout(10, 10));
        rightColumn.setOpaque(false);

        JPanel quickActionCard = new JPanel(new BorderLayout(8, 8));
        quickActionCard.setBackground(COLOR_CARD_OVERVIEW);
        quickActionCard.setBorder(createCustomTitledBorder("Quick Actions", COLOR_CYAN));

        JPanel quickBtnGrid = new JPanel(new GridLayout(4, 2, 10, 10));
        quickBtnGrid.setOpaque(false);
        quickBtnGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton quickEmergencyBtn = createStyledButton("🚨 Code Blue Alert", COLOR_RED, Color.WHITE);
        JButton quickBloodBtn     = createStyledButton("🩸 Blood Search", new Color(180, 40, 40), Color.WHITE);
        JButton quickRegBtn       = createStyledButton("➕ Register Patient", COLOR_CYAN, Color.WHITE);
        JButton quickAppBtn       = createStyledButton("📅 Book Appointment", COLOR_VIOLET, Color.WHITE);
        JButton quickBillBtn      = createStyledButton("💳 Quick Billing", COLOR_PRIMARY_BLUE, Color.WHITE);
        JButton quickPaymentBtn   = createStyledButton("💵 Collect Payment", COLOR_GREEN, Color.WHITE);
        JButton quickBedBtn       = createStyledButton("🛏️ Allocate Bed", COLOR_SLATE, Color.WHITE);
        JButton quickReportBtn    = createStyledButton("📊 Daily Report", COLOR_ORANGE, Color.WHITE);

        quickBtnGrid.add(quickEmergencyBtn);
        quickBtnGrid.add(quickBloodBtn);
        quickBtnGrid.add(quickRegBtn);
        quickBtnGrid.add(quickAppBtn);
        quickBtnGrid.add(quickBillBtn);
        quickBtnGrid.add(quickPaymentBtn);
        quickBtnGrid.add(quickBedBtn);
        quickBtnGrid.add(quickReportBtn);

        quickActionCard.add(quickBtnGrid, BorderLayout.NORTH);

        JPanel bottomRightPanel = new JPanel(new GridLayout(2, 1, 8, 8));
        bottomRightPanel.setOpaque(false);

        JPanel noticeCard = new JPanel(new BorderLayout(6, 6));
        noticeCard.setBackground(COLOR_CARD_OVERVIEW);
        noticeCard.setBorder(createCustomTitledBorder("📢 Hospital Notice Board & Broadcast", COLOR_ORANGE));

        noticeDisplayArea = new JTextArea(2, 20);
        noticeDisplayArea.setEditable(false);
        noticeDisplayArea.setLineWrap(true);
        noticeDisplayArea.setWrapStyleWord(true);
        noticeDisplayArea.setFont(new Font("Segoe UI", Font.BOLD, 11));
        noticeDisplayArea.setForeground(new Color(180, 83, 9)); 
        noticeDisplayArea.setBackground(new Color(254, 243, 199)); 
        noticeDisplayArea.setText("📌 [NOTICE] All doctors are requested to attend the clinical review meeting today at 4:00 PM.");
        noticeDisplayArea.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        JScrollPane noticeScroll = new JScrollPane(noticeDisplayArea);
        noticeScroll.setBorder(BorderFactory.createLineBorder(COLOR_ORANGE, 1));

        JPanel noticeInputPanel = new JPanel(new BorderLayout(4, 4));
        noticeInputPanel.setOpaque(false);
        JTextField noticeInputField = createStyledTextField("Type new official notice...", COLOR_ORANGE);
        JButton postNoticeBtn = createStyledButton("Post Notice", COLOR_ORANGE, Color.WHITE);

        noticeInputPanel.add(noticeInputField, BorderLayout.CENTER);
        noticeInputPanel.add(postNoticeBtn, BorderLayout.EAST);

        noticeCard.add(noticeScroll, BorderLayout.CENTER);
        noticeCard.add(noticeInputPanel, BorderLayout.SOUTH);

        JPanel emergencyCard = new JPanel(new BorderLayout(6, 6));
        emergencyCard.setBackground(COLOR_CARD_OVERVIEW);
        emergencyCard.setBorder(createCustomTitledBorder("🚨 Emergency Bed Requests & Priority Queue", COLOR_RED));

        String[] emCols = {"Patient Name", "Required Type", "Priority Level", "Status"};
        emergencyTableModel = new DefaultTableModel(emCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        emergencyTableModel.addRow(new Object[]{"Sultana Begum", "Isolation Ward", "HIGH (Critical)", "Pending"});
        emergencyTableModel.addRow(new Object[]{"Kamal Uddin", "VIP Single Cabin", "MEDIUM", "Pending"});

        JTable emergencyTable = new JTable(emergencyTableModel);
        emergencyTable.setBackground(COLOR_CARD_WHITE);
        emergencyTable.setForeground(COLOR_TEXT_DARK);
        emergencyTable.setGridColor(COLOR_BORDER);
        emergencyTable.setRowHeight(24);
        emergencyTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        emergencyTable.getTableHeader().setBackground(COLOR_RED);
        emergencyTable.getTableHeader().setForeground(Color.WHITE);
        emergencyTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));

        emergencyTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setFont(new Font("Segoe UI", Font.BOLD, 11));

                String val = String.valueOf(value);
                if (val.contains("HIGH") || val.contains("Critical")) {
                    l.setForeground(COLOR_RED);
                } else if (val.contains("MEDIUM")) {
                    l.setForeground(COLOR_ORANGE);
                } else {
                    l.setForeground(COLOR_CYAN);
                }
                return l;
            }
        });

        JScrollPane emScroll = new JScrollPane(emergencyTable);
        emScroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        JPanel emControlBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 2));
        emControlBar.setOpaque(false);

        JButton addEmRequestBtn = createStyledButton("➕ Add Request", COLOR_RED, Color.WHITE);
        JButton resolveEmBtn = createStyledButton("✓ Allocate & Clear", COLOR_GREEN, Color.WHITE);

        emControlBar.add(addEmRequestBtn);
        emControlBar.add(resolveEmBtn);

        emergencyCard.add(emScroll, BorderLayout.CENTER);
        emergencyCard.add(emControlBar, BorderLayout.SOUTH);

        bottomRightPanel.add(noticeCard);
        bottomRightPanel.add(emergencyCard);

        rightColumn.add(quickActionCard, BorderLayout.NORTH);
        rightColumn.add(bottomRightPanel, BorderLayout.CENTER);

        centerGrid.add(rightColumn);
        centerGrid.add(chartCard);

        panel.add(centerGrid, BorderLayout.CENTER);

        quickEmergencyBtn.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Emergency Code Blue Alert Sent to Medical Staff!", "Emergency Alert", JOptionPane.WARNING_MESSAGE)
        );

        quickBloodBtn.addActionListener(e -> {
            String bg = JOptionPane.showInputDialog(this, "Enter Blood Group (e.g., A+, O-, B+):");
            if (bg != null && !bg.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Checking Blood Bank Stock for: " + bg.toUpperCase());
            }
        });

        quickRegBtn.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        quickAppBtn.addActionListener(e -> tabbedPane.setSelectedIndex(3));
        quickBillBtn.addActionListener(e -> tabbedPane.setSelectedIndex(6));

        quickPaymentBtn.addActionListener(e -> {
            String pName = JOptionPane.showInputDialog(this, "Enter Patient Name/ID:");
            if (pName != null && !pName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Payment Recorded for: " + pName);
            }
        });

        quickBedBtn.addActionListener(e -> tabbedPane.setSelectedIndex(5));

        quickReportBtn.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Exporting Daily Operations Report (PDF)...", "Report Export", JOptionPane.INFORMATION_MESSAGE)
        );

        postNoticeBtn.addActionListener(e -> {
            String newNotice = noticeInputField.getText().trim();
            if (!newNotice.isEmpty() && !newNotice.equals("Type new official notice...")) {
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
                noticeDisplayArea.setText("📌 [" + timeStamp + "] " + newNotice);
                JOptionPane.showMessageDialog(this, "Notice Posted Successfully!");
                noticeInputField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid notice!");
            }
        });

        addEmRequestBtn.addActionListener(e -> {
            JTextField pNameField = new JTextField();
            JComboBox<String> bedTypeCombo = new JComboBox<>(new String[]{"VIP Single", "Deluxe AC", "Male Ward", "Female Ward", "Pediatric Ward", "Isolation Ward"});
            JComboBox<String> priorityCombo = new JComboBox<>(new String[]{"HIGH (Critical)", "MEDIUM", "LOW"});

            Object[] message = {
                "Patient Name:", pNameField,
                "Bed / Sub-Category Type:", bedTypeCombo,
                "Priority Level:", priorityCombo
            };

            int option = JOptionPane.showConfirmDialog(this, message, "New Emergency Bed Request", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION && !pNameField.getText().trim().isEmpty()) {
                emergencyTableModel.addRow(new Object[]{
                    pNameField.getText().trim(),
                    bedTypeCombo.getSelectedItem(),
                    priorityCombo.getSelectedItem(),
                    "Pending"
                });
                JOptionPane.showMessageDialog(this, "Emergency Request Added to Priority Queue!");
            }
        });

        resolveEmBtn.addActionListener(e -> {
            int selectedRow = emergencyTable.getSelectedRow();
            if (selectedRow >= 0) {
                emergencyTableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Emergency Request Allocated & Cleared!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select an emergency request row from the list!");
            }
        });

        return panel;
    }

    private JPanel createCombinedPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(true);
        panel.setBackground(COLOR_BG_PATIENT_HUB);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel regFormCard = new JPanel(new GridBagLayout());
        regFormCard.setBackground(COLOR_CARD_PATIENT_HUB);
        regFormCard.setBorder(createCustomTitledBorder("Register New Patient", COLOR_CYAN));
        regFormCard.setPreferredSize(new Dimension(380, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JTextField pName = createStyledTextField("", COLOR_CYAN);
        JTextField pAge = createStyledTextField("", COLOR_CYAN);
        JTextField pDisease = createStyledTextField("", COLOR_CYAN);
        JButton pBtn = createStyledButton("➕ Add Patient", COLOR_CYAN, Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0; regFormCard.add(createStyledLabel("Patient Name:"), gbc);
        gbc.gridx = 0; gbc.gridy = 1; regFormCard.add(pName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; regFormCard.add(createStyledLabel("Age:"), gbc);
        gbc.gridx = 0; gbc.gridy = 3; regFormCard.add(pAge, gbc);

        gbc.gridx = 0; gbc.gridy = 4; regFormCard.add(createStyledLabel("Disease / Diagnosis:"), gbc);
        gbc.gridx = 0; gbc.gridy = 5; regFormCard.add(pDisease, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.insets = new Insets(20, 8, 8, 8);
        regFormCard.add(pBtn, gbc);

        JPanel dirCard = new JPanel(new BorderLayout(10, 10));
        dirCard.setBackground(COLOR_CARD_PATIENT_HUB);
        dirCard.setBorder(createCustomTitledBorder("Patient Directory & Records", COLOR_CYAN));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setOpaque(false);

        JTextField searchField = createStyledTextField("", COLOR_CYAN);
        searchField.setPreferredSize(new Dimension(250, 32));
        JLabel searchLabel = createStyledLabel("🔍 Search Patient:");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        String[] cols = {"Name", "Age", "Disease / Diagnosis"};
        patientTableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(patientTableModel);
        table.setBackground(COLOR_CARD_WHITE);
        table.setForeground(COLOR_TEXT_DARK);
        table.setGridColor(COLOR_BORDER);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        table.getTableHeader().setBackground(COLOR_CYAN);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(patientTableModel);
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText().trim();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        JScrollPane pane = new JScrollPane(table);
        pane.getViewport().setBackground(COLOR_CARD_WHITE);
        pane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        dirCard.add(searchPanel, BorderLayout.NORTH);
        dirCard.add(pane, BorderLayout.CENTER);

        panel.add(regFormCard, BorderLayout.WEST);
        panel.add(dirCard, BorderLayout.CENTER);

        pBtn.addActionListener(e -> {
            try {
                String name = pName.getText().trim();
                int age = Integer.parseInt(pAge.getText().trim());
                String disease = pDisease.getText().trim();

                if (!name.isEmpty()) {
                    Patient p = new Patient(name, age, disease);
                    patientList.add(p);
                    appPatientCombo.addItem(p.getName());
                    billPatientCombo.addItem(p.getName());

                    patientTableModel.addRow(new Object[]{p.getName(), p.getAge(), p.getDisease()});
                    totalPatientsVal.setText(String.valueOf(patientList.size()));

                    JOptionPane.showMessageDialog(this, "Patient Registered Successfully!");
                    pName.setText(""); pAge.setText(""); pDisease.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter patient name!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numerical age!");
            }
        });

        return panel;
    }

    private JPanel createDoctorRegistrationPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(true);
        panel.setBackground(COLOR_BG_DOCTOR_REG);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBackground(COLOR_CARD_DOCTOR_REG);
        formCard.setBorder(createCustomTitledBorder("Register New Doctor & Schedule", COLOR_VIOLET));
        formCard.setPreferredSize(new Dimension(380, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JTextField dName = createStyledTextField("", COLOR_VIOLET);
        JTextField dSpec = createStyledTextField("", COLOR_VIOLET);
        JTextField dTime = createStyledTextField("10:00 AM - 04:00 PM", COLOR_VIOLET);
        JButton dBtn = createStyledButton("➕ Add Doctor", COLOR_VIOLET, Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0; formCard.add(createStyledLabel("Doctor Name:"), gbc);
        gbc.gridx = 0; gbc.gridy = 1; formCard.add(dName, gbc);
        gbc.gridx = 0; gbc.gridy = 2; formCard.add(createStyledLabel("Specialty:"), gbc);
        gbc.gridx = 0; gbc.gridy = 3; formCard.add(dSpec, gbc);
        gbc.gridx = 0; gbc.gridy = 4; formCard.add(createStyledLabel("Available Hours:"), gbc);
        gbc.gridx = 0; gbc.gridy = 5; formCard.add(dTime, gbc);
        gbc.gridx = 0; gbc.gridy = 6; gbc.insets = new Insets(20, 10, 10, 10); formCard.add(dBtn, gbc);

        JPanel dirCard = new JPanel(new BorderLayout(10, 10));
        dirCard.setBackground(COLOR_CARD_DOCTOR_REG);
        dirCard.setBorder(createCustomTitledBorder("Doctor Directory & Records", COLOR_VIOLET));

        String[] cols = {"Doctor Name", "Specialty", "Available Time"};
        doctorTableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(doctorTableModel);
        table.setBackground(COLOR_CARD_WHITE);
        table.setForeground(COLOR_TEXT_DARK);
        table.setGridColor(COLOR_BORDER);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        table.getTableHeader().setBackground(COLOR_VIOLET);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane pane = new JScrollPane(table);
        pane.getViewport().setBackground(COLOR_CARD_WHITE);
        pane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        dirCard.add(pane, BorderLayout.CENTER);

        panel.add(formCard, BorderLayout.WEST);
        panel.add(dirCard, BorderLayout.CENTER);

        dBtn.addActionListener(e -> {
            String name = dName.getText().trim();
            String spec = dSpec.getText().trim();
            String time = dTime.getText().trim();

            if (!name.isEmpty() && !time.isEmpty()) {
                Doctor d = new Doctor(name, spec, time);
                doctorList.add(d);
                appDoctorCombo.addItem(d.getName());
                
                doctorTableModel.addRow(new Object[]{name, spec, time});

                totalDoctorsVal.setText(String.valueOf(doctorList.size()));

                JOptionPane.showMessageDialog(this, "Doctor Registered Successfully!");
                dName.setText(""); dSpec.setText(""); dTime.setText("10:00 AM - 04:00 PM");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter doctor name and available time!");
            }
        });

        return panel;
    }

    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(true);
        panel.setBackground(COLOR_BG_APPOINTMENTS);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = new JPanel(new GridLayout(7, 2, 8, 8));
        form.setBackground(COLOR_CARD_APPOINTMENTS);
        form.setBorder(createCustomTitledBorder("Schedule New Appointment", COLOR_ORANGE));

        JTextField dateField = createStyledTextField("2026-07-26", COLOR_ORANGE);
        JTextField timeField = createStyledTextField("10:30 AM", COLOR_ORANGE);
        JButton bookBtn = createStyledButton("Book Appointment", COLOR_ORANGE, Color.WHITE);

        doctorScheduleDisplay.setFont(new Font("Segoe UI", Font.BOLD, 12));
        doctorScheduleDisplay.setForeground(COLOR_ORANGE);

        form.add(createStyledLabel("Select Patient:")); form.add(createStyledComboBox(appPatientCombo));
        form.add(createStyledLabel("Select Doctor:")); form.add(createStyledComboBox(appDoctorCombo));
        form.add(createStyledLabel("Doctor Hours:")); form.add(doctorScheduleDisplay);
        form.add(createStyledLabel("Date:")); form.add(dateField);
        form.add(createStyledLabel("Time Slot:")); form.add(timeField);
        form.add(new JLabel("")); form.add(bookBtn);

        appDoctorCombo.addActionListener(e -> {
            int selIdx = appDoctorCombo.getSelectedIndex();
            if (selIdx >= 0 && selIdx < doctorList.size()) {
                doctorScheduleDisplay.setText(doctorList.get(selIdx).getAvailableTime());
            }
        });

        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBackground(COLOR_CARD_APPOINTMENTS);
        tablePanel.setBorder(createCustomTitledBorder("Live Patient Queue & Appointment Status", COLOR_ORANGE));

        String[] cols = {"Patient Name", "Assigned Doctor", "Date", "Time", "Queue Status"};
        appointmentTableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(appointmentTableModel);
        table.setBackground(COLOR_CARD_WHITE);
        table.setForeground(COLOR_TEXT_DARK);
        table.setGridColor(COLOR_BORDER);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        table.getTableHeader().setBackground(COLOR_ORANGE);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setFont(new Font("Segoe UI", Font.BOLD, 12));

                String val = String.valueOf(value);
                if ("Waiting".equalsIgnoreCase(val)) {
                    l.setForeground(COLOR_ORANGE);
                } else if ("In Consultation".equalsIgnoreCase(val)) {
                    l.setForeground(COLOR_PRIMARY_BLUE);
                } else if ("Completed".equalsIgnoreCase(val)) {
                    l.setForeground(COLOR_CYAN);
                }
                return l;
            }
        });

        JScrollPane pane = new JScrollPane(table);
        pane.getViewport().setBackground(COLOR_CARD_WHITE);
        pane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        JPanel queueControlBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        queueControlBar.setOpaque(false);

        JComboBox<String> statusSelector = createStyledComboBox(new JComboBox<>(new String[]{"Waiting", "In Consultation", "Completed"}));
        JButton updateStatusBtn = createStyledButton("Update Patient Status", COLOR_ORANGE, Color.WHITE);

        queueControlBar.add(createStyledLabel("Update Queue Status:"));
        queueControlBar.add(statusSelector);
        queueControlBar.add(updateStatusBtn);

        tablePanel.add(pane, BorderLayout.CENTER);
        tablePanel.add(queueControlBar, BorderLayout.SOUTH);

        panel.add(form, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);

        bookBtn.addActionListener(e -> {
            if (appPatientCombo.getItemCount() > 0 && appDoctorCombo.getItemCount() > 0) {
                String pName = (String) appPatientCombo.getSelectedItem();
                String dName = (String) appDoctorCombo.getSelectedItem();
                String dDate = dateField.getText().trim();
                String dTime = timeField.getText().trim();
                String initialStatus = "Waiting";

                Appointment app = new Appointment(pName, dName, dDate, dTime, initialStatus);
                appointmentList.add(app);

                appointmentTableModel.addRow(new Object[]{pName, dName, dDate, dTime, initialStatus});
                JOptionPane.showMessageDialog(this, "Appointment Booked!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a registered Patient and Doctor!");
            }
        });

        updateStatusBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String newStatus = (String) statusSelector.getSelectedItem();
                appointmentTableModel.setValueAt(newStatus, selectedRow, 4);
                appointmentList.get(selectedRow).setStatus(newStatus);
                JOptionPane.showMessageDialog(this, "Patient Status Updated: " + newStatus);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an appointment row first!");
            }
        });

        return panel;
    }

    private JPanel createStaffShiftPanel() {
    JPanel panel = new JPanel(new BorderLayout(15, 15));
    panel.setOpaque(true);
    panel.setBackground(COLOR_BG_STAFF);
    panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JPanel formCard = new JPanel(new GridLayout(2, 5, 8, 8));
    formCard.setBackground(new Color(248, 250, 255));
    formCard.setBorder( createCustomTitledBorder( "Assign New Staff & Roster", new Color(99, 102, 241))
    );


    JTextField staffNameField = createStyledTextField( "",new Color(59, 130, 246) );

    staffNameField.setBackground(new Color(239, 246, 255) );

    staffNameField.setForeground(new Color(30, 64, 175));

    staffNameField.setCaretColor(new Color(37, 99, 235));

    staffNameField.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(59, 130, 246),2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8 ) ));


    JTextField roleField =createStyledTextField("Staff Nurse",new Color(139, 92, 246));

    roleField.setBackground(new Color(245, 240, 255) );

    roleField.setForeground( new Color(109, 40, 217));

    roleField.setCaretColor(new Color(124, 58, 237) );

            roleField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(139, 92, 246),2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)) );


            JComboBox<String> shiftCombo =createStyledComboBox(new JComboBox<>(
                new String[]{"Morning (8AM-4PM)", "Evening (4PM-12AM)","Night (12AM-8AM)"}) );

           shiftCombo.setBackground( new Color(255, 247, 237));

           shiftCombo.setForeground(new Color(194, 65, 12));

           shiftCombo.setBorder( BorderFactory.createLineBorder(new Color(249, 115, 22),2 ));


          JComboBox<String> statusCombo =createStyledComboBox(new JComboBox<>( new String[]
         {"On-Duty","On-Call","Off-Duty" }));

              statusCombo.setBackground(new Color(236, 253, 245));

              statusCombo.setForeground(new Color(5, 150, 105) );

              statusCombo.setBorder(BorderFactory.createLineBorder(new Color(16, 185, 129), 2));

             JButton addStaffBtn =createStyledButton( "Add To Duty Roster", new Color(79, 70, 229), Color.WHITE );


             JLabel staffNameLabel =createStyledLabel("Staff Name:");

             staffNameLabel.setForeground(new Color(37, 99, 235) );

             JLabel roleLabel = createStyledLabel("Role/Designation:");

             roleLabel.setForeground(new Color(124, 58, 237) );

             JLabel shiftLabel =createStyledLabel("Assigned Shift:");

            shiftLabel.setForeground( new Color(234, 88, 12));

            JLabel statusLabel = createStyledLabel("Duty Status:");

            statusLabel.setForeground(new Color(5, 150, 105));

            formCard.add(staffNameLabel);
            formCard.add(roleLabel);
            formCard.add(shiftLabel);
            formCard.add(statusLabel);
            formCard.add(new JLabel(""));

            formCard.add(staffNameField);
            formCard.add(roleField);
            formCard.add(shiftCombo);
            formCard.add(statusCombo);
            formCard.add(addStaffBtn);

 
            JPanel centerPanel =new JPanel(new GridLayout(1, 2, 15, 15));

            centerPanel.setOpaque(false);

            JPanel rosterCard =new JPanel( new BorderLayout(10, 10));

            rosterCard.setBackground(new Color(248, 250, 255));

            rosterCard.setBorder(createCustomTitledBorder(
                    "Current Duty Roster & Status",new Color(99, 102, 241)));

            String[] cols = {"Name","Role","Shift Slot","Duty Status"};

            staffTableModel =new DefaultTableModel(cols, 0)
            {
            @Override
             public boolean isCellEditable(int row,int column ) {
                        return false;
                    }
                };

            for (StaffMember sm : staffList) {

        staffTableModel.addRow( new Object[]{ sm.getName(),sm.getRole(),sm.getShift(),sm.getStatus()});}

            JTable table =new JTable(staffTableModel );
            table.setBackground(new Color(248, 250, 252));
            table.setForeground(COLOR_TEXT_DARK);
            table.setGridColor(new Color(226, 232, 240));
            table.setRowHeight(34);
            table.setFont(new Font("Segoe UI",Font.PLAIN,12));
            table.setSelectionBackground(new Color(219, 234, 254));
            table.setSelectionForeground(new Color(15, 23, 42));
            table.getTableHeader().setBackground(new Color(67, 56, 202));
            table.getTableHeader().setForeground(Color.WHITE);
            table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));

            table.setDefaultRenderer(Object.class,new DefaultTableCellRenderer() {
            @Override
            public Component
            getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column
               )   {

                JLabel l =(JLabel) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row, column);
                l.setOpaque(true);
                l.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8)
                );
                if (isSelected) {

                    l.setBackground(new Color(219, 234, 254) );
                    l.setForeground(new Color(15, 23, 42));

                } else {

                    if (row % 2 == 0) {
                          l.setBackground(new Color(248, 250, 252)
                        );

                    } else {

                        l.setBackground(new Color(239, 246, 255) );
                    }

                    l.setForeground( new Color(30, 41, 59));
                }

                if (column == 0) {l.setFont(new Font("Segoe UI", Font.BOLD,12 ) );
                    l.setForeground(new Color(30, 64, 175));
                }
                if (column == 1) { l.setBackground( new Color(245, 235, 255) );
                    l.setForeground(new Color(109, 40, 217));
                    l.setFont(new Font("Segoe UI",Font.BOLD,12 ));
                }
                if (column == 2) { String shift = String.valueOf(value);

                    if (shift.contains("Morning")
                    ) { l.setBackground(new Color(224, 242, 254));

                        l.setForeground(new Color(3, 105, 161));

                    } else if (shift.contains("Evening")) {

                        l.setBackground(new Color(255, 237, 213));

                        l.setForeground(new Color(194, 65, 12) );

                    } else {l.setBackground(new Color(224, 231, 255));

                        l.setForeground( new Color(55, 48, 163));
                    }

                    l.setFont( new Font("Segoe UI",Font.BOLD, 11 ));
                }

                if (column == 3) {

                    l.setHorizontalAlignment(SwingConstants.CENTER );
                    l.setFont(new Font("Segoe UI",Font.BOLD,12));

                    String val =String.valueOf(value);

                    if ("On-Duty".equalsIgnoreCase(val)) {

                        l.setBackground(new Color(220, 252, 231));

                        l.setForeground(new Color(22, 101, 52));

                    } else if ("On-Call".equalsIgnoreCase(val)) {

                        l.setBackground(new Color(254, 249, 195));

                        l.setForeground(new Color(133, 77, 14));

                    } else {

                        l.setBackground(new Color(254, 226, 226) );

                        l.setForeground(new Color(153, 27, 27) );
                    }
                }

                return l;
            }
        }
    );

                JScrollPane rosterScroll =new JScrollPane(table);
                rosterScroll.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225),1) );
                JPanel statusUpdateBar =new JPanel(new FlowLayout(FlowLayout.RIGHT,8,5));
                statusUpdateBar.setOpaque(false);
                JComboBox<String> newStatusCombo =createStyledComboBox( new JComboBox<>(new String[]{
                                "On-Duty","On-Call","Off-Duty"} ));

                newStatusCombo.setBackground(new Color(236, 253, 245));
                newStatusCombo.setForeground(new Color(5, 150, 105));
                newStatusCombo.setBorder(BorderFactory.createLineBorder(new Color(16, 185, 129),2)
                );
                JButton updateStatusBtn =createStyledButton("Update Status",new Color(37, 99, 235),Color.WHITE);
                statusUpdateBar.add(createStyledLabel("Change Status:"));
                statusUpdateBar.add(newStatusCombo);
                statusUpdateBar.add(updateStatusBtn);
                rosterCard.add(rosterScroll,BorderLayout.CENTER );
                rosterCard.add(statusUpdateBar,BorderLayout.SOUTH);
                JPanel handoverCard =new JPanel(new BorderLayout(10, 10));
                handoverCard.setBackground(new Color(248, 250, 255));
                handoverCard.setBorder( createCustomTitledBorder("Shift Handover & Emergency Notes",new Color(99, 102, 241) ));
                JTextArea handoverNotes = new JTextArea();
                handoverNotes.setFont(new Font("Segoe UI",Font.PLAIN,13));
                handoverNotes.setBackground(new Color(255, 251, 235));
                handoverNotes.setForeground( new Color(71, 85, 105));
                handoverNotes.setCaretColor(new Color(124, 58, 237));
                handoverNotes.setText("- Morning Shift Note: ICU Bed #3 patient needs "
                    + "ventilator check at 2:00 PM.\n"
                    + "- Evening Duty Doctor: Dr. Rahman will cover "
                    + "emergency ward until 6:00 PM.\n");

                handoverNotes.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(245, 158, 11),1 ),
            BorderFactory.createEmptyBorder(8, 8, 8, 8 )));

            JScrollPane noteScroll =new JScrollPane(handoverNotes );

            noteScroll.setBorder( BorderFactory.createLineBorder( new Color(245, 158, 11),1));
            JButton saveNotesBtn =createStyledButton(    "Save & Broadcast Notes",new Color(124, 58, 237),Color.WHITE
                );

            handoverCard.add(noteScroll,BorderLayout.CENTER);

            handoverCard.add(saveNotesBtn,BorderLayout.SOUTH);

            centerPanel.add(rosterCard);
            centerPanel.add(handoverCard);

            panel.add(formCard, BorderLayout.NORTH);
            panel.add(centerPanel,BorderLayout.CENTER);
        addStaffBtn.addActionListener(e -> {

        String name =staffNameField.getText().trim();

        String role =roleField.getText().trim();

        String shift =(String)shiftCombo.getSelectedItem();

        String status =(String)statusCombo.getSelectedItem();

        if (!name.isEmpty() &&!role.isEmpty()) {

            StaffMember sm =new StaffMember(name,role,shift, status);

            staffList.add(sm);

            staffTableModel.addRow(
                new Object[]{
                    name,
                    role,
                    shift,
                    status
                }
            );

            JOptionPane.showMessageDialog(
                this,
                "Staff Added to Duty Roster!"
            );

            staffNameField.setText("");

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Please enter staff name and role!"
            );
        }
    });
    updateStatusBtn.addActionListener(e -> {

        int selRow =
            table.getSelectedRow();

        if (selRow >= 0) {

            String newStatus =
                (String)
                    newStatusCombo
                        .getSelectedItem();

            staffTableModel.setValueAt(
                newStatus,
                selRow,
                3
            );

            staffList
                .get(selRow)
                .setStatus(newStatus);

            table.repaint();

            JOptionPane.showMessageDialog(
                this,
                "Staff Status Updated!"
            );

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Please select a staff row from table!"
            );
        }
    });
    saveNotesBtn.addActionListener(e ->
        JOptionPane.showMessageDialog(
            this,
            "Shift Handover Notes Saved Successfully!"
        )
    );

    return panel;
}

    private JPanel createTrackerPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(true);
        panel.setBackground(COLOR_BG_WARD_CABIN); 
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 8));
        filterPanel.setBackground(COLOR_CARD_TEAL);
        filterPanel.setBorder(createCustomTitledBorder("Filter Cabin & Ward Sub-Categories", COLOR_PRIMARY_BLUE));

        JComboBox<String> categoryFilter = createStyledComboBox(new JComboBox<>(new String[]{
            "All Categories", "Cabin", "Ward", "VIP Single", "Deluxe AC", "Double AC", "Non-AC Cabin",
            "Male Ward", "Female Ward", "Pediatric (Child) Ward", "Isolation Ward"
        }));

        filterPanel.add(createStyledLabel("Filter by Type / Sub-category:"));
        filterPanel.add(categoryFilter);

        String[] trackerCols = {
            "ID", "Category", "Sub-Category", "Status", 
            "Patient Identifier (Tag)", "Admission Date", "Assigned Staff", "Life Support & Equipment"
        };

        trackerTableModel = new DefaultTableModel(trackerCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable trackerTable = new JTable(trackerTableModel);
        trackerTable.getTableHeader().setBackground(new Color(13, 148, 136));
trackerTable.getTableHeader().setForeground(Color.WHITE);
        trackerTable.setGridColor(COLOR_BORDER);
        trackerTable.setRowHeight(32);
        trackerTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        trackerTable.getTableHeader().setBackground(COLOR_PRIMARY_BLUE);
        trackerTable.getTableHeader().setForeground(Color.WHITE);
        trackerTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        trackerTable.setSelectionBackground(new Color(219, 234, 254));
        trackerTable.setSelectionForeground(COLOR_TEXT_DARK);

        trackerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

            label.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

        if (isSelected) {
            label.setBackground(new Color(219, 234, 254));
            label.setForeground(COLOR_TEXT_DARK);
        } else if (row % 2 == 0) {
          
            label.setBackground(new Color(248, 251, 255));
            label.setForeground(COLOR_TEXT_DARK);
        } else {
         
            label.setBackground(new Color(240, 253, 250));
            label.setForeground(COLOR_TEXT_DARK);
        }

        return label;
            }
        });
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(trackerTableModel);
        trackerTable.setRowSorter(sorter);

       

        refreshTrackerTable();

        JScrollPane scrollPane = new JScrollPane(trackerTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        actionPanel.setOpaque(false);

        JButton admitBtn = createStyledButton("Admit / Assign Patient", COLOR_VIOLET, Color.WHITE);
        JButton releaseBtn = createStyledButton("Discharge Patient", COLOR_RED, Color.WHITE);
        JButton addNewBedBtn = createStyledButton("➕ Add New Room/Bed", COLOR_PRIMARY_BLUE, Color.WHITE);

        actionPanel.add(admitBtn);
        actionPanel.add(releaseBtn);
        actionPanel.add(addNewBedBtn);

        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);

        categoryFilter.addActionListener(e -> {
            String selected = (String) categoryFilter.getSelectedItem();
            if (selected == null || selected.equals("All Categories")) {
                sorter.setRowFilter(null);
            } else if (selected.equals("Cabin") || selected.equals("Ward")) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + selected + "$", 1));
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + selected, 2));
            }
        });

        admitBtn.addActionListener(e -> {
            int selRow = trackerTable.getSelectedRow();
            if (selRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select a Cabin or Bed from the table first!");
                return;
            }

            int modelRow = trackerTable.convertRowIndexToModel(selRow);
            Bed bed = dashboardState.getBedList().get(modelRow);

            if (bed.isOccupied()) {
                JOptionPane.showMessageDialog(this, "This Bed/Cabin is already occupied!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JTextField pIdNameField = new JTextField();
            JTextField dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            JTextField staffField = new JTextField("Dr. Rahman / Nurse Sarah");

            Object[] inputFields = {
                "Patient Identifier (ID / Name):", pIdNameField,
                "Admission Date (YYYY-MM-DD):", dateField,
                "Assigned Doctor / Nurse:", staffField
            };

            int option = JOptionPane.showConfirmDialog(this, inputFields, "Admit Patient to " + bed.getId(), JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION && !pIdNameField.getText().trim().isEmpty()) {
                bed.assignPatient(pIdNameField.getText().trim(), dateField.getText().trim(), staffField.getText().trim());
                refreshTrackerTable();
                updateKpiLabels();
                chartPanel.repaint();
                JOptionPane.showMessageDialog(this, "Patient Admitted & Assigned to " + bed.getId() + " Successfully!");
            }
        });

        releaseBtn.addActionListener(e -> {
            int selRow = trackerTable.getSelectedRow();
            if (selRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select an occupied Cabin or Bed from the table!");
                return;
            }

            int modelRow = trackerTable.convertRowIndexToModel(selRow);
            Bed bed = dashboardState.getBedList().get(modelRow);

            if (!bed.isOccupied()) {
                JOptionPane.showMessageDialog(this, "This Bed/Cabin is already vacant!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Discharge patient " + bed.getPatientIdName() + " from " + bed.getId() + "?", "Confirm Discharge", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                bed.releasePatient();
                refreshTrackerTable();
                updateKpiLabels();
                chartPanel.repaint();
                JOptionPane.showMessageDialog(this, "Patient Discharged. Bed is now Available.");
            }
        });

        addNewBedBtn.addActionListener(e -> {
            JTextField bedIdField = new JTextField();
            JComboBox<String> mainCatCombo = new JComboBox<>(new String[]{"Cabin", "Ward"});
            JComboBox<String> subCatCombo = new JComboBox<>(new String[]{
                "VIP Single", "Deluxe AC", "Double AC", "Non-AC Cabin",
                "Male Ward", "Female Ward", "Pediatric (Child) Ward", "Isolation Ward"
            });
            JCheckBox ventCheck = new JCheckBox("Ventilator Support");
            JCheckBox monCheck = new JCheckBox("Patient Monitor");
            JCheckBox oxyCheck = new JCheckBox("Central Oxygen");

            Object[] addFields = {
                "Bed/Cabin ID (e.g. C-105, W-205):", bedIdField,
                "Category:", mainCatCombo,
                "Sub-Category:", subCatCombo,
                "Equipment Support:", ventCheck, monCheck, oxyCheck
            };

            int option = JOptionPane.showConfirmDialog(this, addFields, "Add New Room / Bed", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION && !bedIdField.getText().trim().isEmpty()) {
                Bed newBed = new Bed(
                    bedIdField.getText().trim(),
                    (String) mainCatCombo.getSelectedItem(),
                    (String) subCatCombo.getSelectedItem(),
                    ventCheck.isSelected(),
                    monCheck.isSelected(),
                    oxyCheck.isSelected()
                );
                dashboardState.getBedList().add(newBed);
                refreshTrackerTable();
                updateKpiLabels();
                chartPanel.repaint();
                JOptionPane.showMessageDialog(this, "New Room/Bed Added Successfully!");
            }
        });

        return panel;
    }

    private void refreshTrackerTable() {
        if (trackerTableModel == null) return;
        trackerTableModel.setRowCount(0);
        for (Bed bed : dashboardState.getBedList()) {
            trackerTableModel.addRow(new Object[]{
                bed.getId(),
                bed.getMainCategory(),
                bed.getSubCategory(),
                bed.isOccupied() ? "Occupied" : "Available",
                bed.getPatientIdName(),
                bed.getAdmissionDate(),
                bed.getAssignedStaff(),
                bed.getSupportSummary()
            });
        }
    }

    private JPanel createBillingPanel() {

          JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

            mainPanel.setOpaque(true);
            mainPanel.setBackground(BILL_BG);

            mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15 )
            );
    
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/hospitalpng.png"));

            Image logoImage =logoIcon.getImage().getScaledInstance(150, 80,Image.SCALE_SMOOTH );

            JLabel logoLabel = new JLabel(new ImageIcon(logoImage)  );

            logoLabel.setHorizontalAlignment(SwingConstants.CENTER );

            JLabel billingTitle = new JLabel("Billing Terminal & Advanced Invoice Generator");

            billingTitle.setFont(new Font("Segoe UI", Font.BOLD,18));

            billingTitle.setForeground(COLOR_PRIMARY_BLUE);

            JPanel billingHeader = new JPanel(new FlowLayout( FlowLayout.LEFT,12,5)
            );

            billingHeader.setOpaque(false);

            billingHeader.add(logoLabel);
            billingHeader.add(billingTitle);

            JPanel formCard =new JPanel(new GridBagLayout() );

            formCard.setBackground( Color.WHITE);

            formCard.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BILL_BLUE,1),
                    BorderFactory.createEmptyBorder( 8,10,10,10))
                                );
            GridBagConstraints headerGbc = new GridBagConstraints();
                               headerGbc.gridx = 0;
                               headerGbc.gridy = 0;

                               headerGbc.gridwidth = 4;

                               headerGbc.weightx = 1.0;

           headerGbc.fill =GridBagConstraints.HORIZONTAL;

           headerGbc.anchor =GridBagConstraints.WEST;

           headerGbc.insets = new Insets(0,0,12,0 );

            formCard.add(billingHeader,headerGbc);
            
            GridBagConstraints gbc =new GridBagConstraints();

             gbc.insets = new Insets(6,4,6,4);

             gbc.fill =GridBagConstraints.HORIZONTAL;

             gbc.anchor =GridBagConstraints.WEST;


            JTextField docFee =createStyledTextField("1000",COLOR_PRIMARY_BLUE);
            JTextField medFee =createStyledTextField("500",COLOR_PRIMARY_BLUE);
            JTextField labFee =createStyledTextField("1200",COLOR_PRIMARY_BLUE);
            JTextField depositField =createStyledTextField("0",COLOR_PRIMARY_BLUE );
            JTextField taxRateField = createStyledTextField("5",COLOR_PRIMARY_BLUE );
            JTextField discountField = createStyledTextField("0",COLOR_PRIMARY_BLUE );

            docFee.setBackground(BILL_GREEN_LIGHT);
            docFee.setBorder(BorderFactory.createLineBorder(new Color(134, 239, 172),1));
            medFee.setBackground(BILL_ORANGE_LIGHT);
            medFee.setBorder(BorderFactory.createLineBorder(new Color(253, 186, 116), 1));
            labFee.setBackground(BILL_PURPLE_LIGHT );
            labFee.setBorder(BorderFactory.createLineBorder( new Color(196, 181, 253),1));
            depositField.setBackground(BILL_GREEN_LIGHT);

            depositField.setBorder(BorderFactory.createLineBorder(new Color(134, 239, 172),1));
            taxRateField.setBackground(BILL_CYAN_LIGHT );
            taxRateField.setBorder(BorderFactory.createLineBorder(new Color(103, 232, 249), 1) );
            discountField.setBackground(BILL_PINK_LIGHT );
            discountField.setBorder( BorderFactory.createLineBorder(new Color(249, 168, 212),1));

            JComboBox<String> paymentMethodCombo =createStyledComboBox(new JComboBox<>(
                                    new String[]{"Cash","Credit/Debit Card","bKash / Nagad","Insurance Claims"}));
            JTextField cabinDaysField =new JTextField("0",3);
            cabinDaysField.setBackground(COLOR_INPUT_BG);

            cabinDaysField.setForeground(COLOR_TEXT_DARK );

            cabinDaysField.setHorizontalAlignment(JTextField.CENTER );
            JButton cabinPlus =createStyledButton( "+", BILL_BLUE,Color.WHITE);
            JButton cabinMinus =createStyledButton( "-", BILL_BLUE,Color.WHITE );
            JPanel cabinControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,2,0));

            cabinControlPanel.setOpaque(false);

            cabinControlPanel.add(cabinMinus);
            cabinControlPanel.add(cabinDaysField);
            cabinControlPanel.add(cabinPlus);

            JTextField wardDaysField = new JTextField("0",3);

            wardDaysField.setBackground(COLOR_INPUT_BG );

            wardDaysField.setForeground( COLOR_TEXT_DARK);

            wardDaysField.setHorizontalAlignment(JTextField.CENTER);


            JButton wardPlus =createStyledButton( "+",BILL_PURPLE,Color.WHITE);

            JButton wardMinus =createStyledButton("-",BILL_PURPLE,Color.WHITE );
            JPanel wardControlPanel =new JPanel(new FlowLayout(FlowLayout.LEFT, 2,0));
            wardControlPanel.setOpaque(false);
            wardControlPanel.add(wardMinus);
            wardControlPanel.add(wardDaysField);
            wardControlPanel.add(wardPlus);

            String[] billOptions = {
                    "Custom Comprehensive Bill",
                    "Standard Cabin Package (4500 Tk/d)",
                    "Standard General Ward Package (1200 Tk/d)"
            };


    JComboBox<String> billtypecombo =createStyledComboBox( new JComboBox<>(billOptions));
    JButton calcBtn =createStyledButton( "Generate Invoice Receipt",BILL_BLUE, Color.WHITE);

    calcBtn.setFont(new Font("Segoe UI", Font.BOLD, 14 )
    );

    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridx = 0;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Patient Name:"),gbc);
    gbc.gridx = 1;
    gbc.weightx = 0.42;
    formCard.add(createStyledComboBox(billPatientCombo),gbc
    );
    gbc.gridx = 2;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Bill Package:" ),gbc );

    gbc.gridx = 3;
    gbc.weightx = 0.42;
    formCard.add( billtypecombo, gbc);

    gbc.gridy = 2;
    gbc.gridx = 0;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Doctor Fee (Tk):"), gbc );
    gbc.gridx = 1;
    gbc.weightx = 0.42;

    formCard.add(docFee, gbc);
    gbc.gridx = 2;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Medicine Fee (Tk):" ), gbc);
    gbc.gridx = 3;
    gbc.weightx = 0.42;

    formCard.add(medFee,gbc);

    gbc.gridy = 3;
    gbc.gridx = 0;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Lab & Test Fee (Tk):" ),gbc );
    gbc.gridx = 1;
    gbc.weightx = 0.42;

    formCard.add(labFee, gbc);
    gbc.gridx = 2;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Advance Deposit (Tk):" ),gbc);
    gbc.gridx = 3;
    gbc.weightx = 0.42;

    formCard.add(depositField,gbc);

    gbc.gridy = 4;
    gbc.gridx = 0;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Cabin Stay (Days):" ), gbc);
    gbc.gridx = 1;
    gbc.weightx = 0.42;

    formCard.add( cabinControlPanel, gbc
    );
    gbc.gridx = 2;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Ward Stay (Days):"),gbc);
    gbc.gridx = 3;
    gbc.weightx = 0.42;

    formCard.add(wardControlPanel,gbc );

    gbc.gridy = 5;
    gbc.gridx = 0;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("VAT / Tax (%):"),
            gbc
    );
    gbc.gridx = 1;
    gbc.weightx = 0.42;

    formCard.add( taxRateField,gbc);
    gbc.gridx = 2;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel( "Discount (Tk):"),gbc);

    gbc.gridx = 3;
    gbc.weightx = 0.42;

    formCard.add(discountField,gbc
    );
    JPanel btnPanel =new JPanel(new FlowLayout( FlowLayout.RIGHT,0, 0));
    btnPanel.setOpaque(false);
    btnPanel.add(calcBtn);
    gbc.gridy = 6;

    gbc.gridx = 0;
    gbc.weightx = 0.08;

    formCard.add(createStyledLabel("Payment Method:"
            ),
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 0.42;

    formCard.add(
            paymentMethodCombo,
            gbc
    );
    gbc.gridx = 2;
    gbc.weightx = 0.08;

    formCard.add(
            new JLabel(""),
            gbc
    );

    gbc.gridx = 3;
    gbc.weightx = 0.42;

    formCard.add(
            btnPanel,
            gbc
    );

    JTextArea receipt = new JTextArea();

    receipt.setEditable(false);

    receipt.setFont(new Font("Monospaced",Font.BOLD,13 ));

    receipt.setBackground(
            COLOR_CARD_WHITE
    );

    receipt.setForeground(
            COLOR_TEXT_DARK
    );

    receipt.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BILL_BLUE,1),
                    BorderFactory.createEmptyBorder( 15, 15,15,15 )
            )
    );
    JScrollPane scrollPane =new JScrollPane(receipt);

    scrollPane.setPreferredSize(new Dimension( 800,260 ) );

    JPanel invoiceContainer =new JPanel(new GridBagLayout());

    invoiceContainer.setBackground( BILL_BLUE_LIGHT );
    JPanel invoiceCard =new JPanel(new BorderLayout());

    invoiceCard.setBackground( Color.WHITE );

    invoiceCard.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BILL_BLUE,1 ),
                    BorderFactory.createEmptyBorder(5, 5,5, 5
                    )
            )
    );

    JLabel invoiceTitle =
            new JLabel(
                    "CAREPULSE  •  INVOICE RECEIPT",
                    SwingConstants.CENTER
            );

    invoiceTitle.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    15
            )
    );

    invoiceTitle.setForeground(
            Color.WHITE
    );

    invoiceTitle.setOpaque(true);

    invoiceTitle.setBackground(
            BILL_BLUE
    );

    invoiceTitle.setBorder(
            BorderFactory.createEmptyBorder( 10, 10,10,10)
    );


    invoiceCard.add(invoiceTitle,BorderLayout.NORTH);

    invoiceCard.add(scrollPane,BorderLayout.CENTER
    );


    invoiceContainer.add(
            invoiceCard
    );

    JPanel topPanel =
            new JPanel(
                    new BorderLayout()
            );

    topPanel.setOpaque(false);

    topPanel.add(
            formCard,
            BorderLayout.CENTER
    );

    mainPanel.add(
            topPanel,
            BorderLayout.NORTH
    );

    mainPanel.add(
            invoiceContainer,
            BorderLayout.CENTER
    );

    cabinPlus.addActionListener(e -> {

        try {

            cabinDaysField.setText(
                    String.valueOf(
                            Integer.parseInt(
                                    cabinDaysField
                                            .getText()
                                            .trim()
                            ) + 1
                    )
            );

        } catch (Exception ex) {

            cabinDaysField.setText("1");
        }
    });
    cabinMinus.addActionListener(e -> {

        try {

            int v =
                    Integer.parseInt(
                            cabinDaysField
                                    .getText()
                                    .trim()
                    );

            if (v > 0) {

                cabinDaysField.setText(
                        String.valueOf(
                                v - 1
                        )
                );
            }

        } catch (Exception ex) {

            cabinDaysField.setText("0");
        }
    });

    wardPlus.addActionListener(e -> {

        try {

            wardDaysField.setText(
                    String.valueOf(
                            Integer.parseInt(
                                    wardDaysField
                                            .getText()
                                            .trim()
                            ) + 1
                    )
            );

        } catch (Exception ex) {

            wardDaysField.setText("1");
        }
    });

    wardMinus.addActionListener(e -> {

        try {

            int v =
                    Integer.parseInt(
                            wardDaysField
                                    .getText()
                                    .trim()
                    );

            if (v > 0) {

                wardDaysField.setText(
                        String.valueOf(
                                v - 1
                        )
                );
            }

        } catch (Exception ex) {

            wardDaysField.setText("0");
        }
    });
    calcBtn.addActionListener(e -> {

        if (billPatientCombo.getItemCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please register a patient first!"
            );

            return;
        }try {int selectedIndex =billtypecombo.getSelectedIndex();int cDays =
                    Integer.parseInt(cabinDaysField .getText().trim()
                    );


            int wDays =
                    Integer.parseInt(
                            wardDaysField
                                    .getText()
                                    .trim()
                    );


            double deposit =
                    Double.parseDouble(
                            depositField
                                    .getText()
                                    .trim()
                    );


            double taxPercent =
                    Double.parseDouble(
                            taxRateField
                                    .getText()
                                    .trim()
                    );


            double discount =
                    Double.parseDouble(
                            discountField
                                    .getText()
                                    .trim()
                    );


            String payMethod =
                    (String)
                            paymentMethodCombo
                                    .getSelectedItem();

            Bill bill;
            if (selectedIndex == 1) { bill =new CabinBill(cDays);}

            else if (selectedIndex == 2) {bill =new GeneralBill(wDays );}

            else {

                double dFee =
                        Double.parseDouble(
                                docFee
                                        .getText()
                                        .trim()
                        );


                double mFee =
                        Double.parseDouble(
                                medFee
                                        .getText()
                                        .trim()
                        );


                double lFee =
                        Double.parseDouble(
                                labFee
                                        .getText()
                                        .trim()
                        );


                bill =
                        new Billing(
                                dFee,
                                mFee,
                                cDays,
                                4500.0,
                                wDays,
                                1200.0,
                                lFee
                        );
            }

            double subtotal = bill.calculateBill();
            double taxAmount = ( subtotal* taxPercent ) / 100.0;
            double grandTotal =( subtotal+ taxAmount)- discount;
            double balance =deposit- grandTotal;

            String invoiceId = "INV-2026-"+(1000+ new Random().nextInt(9000) );
            String dateStr =new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( new Date());

            String output =
                    "==================================================================\n"
                    +
                    "                   CAREPULSE HOSPITAL RECEIPT                   \n"
                    +
                    "==================================================================\n"
                    + " Invoice ID     : " +invoiceId
                    +"\n"  + " Date & Time    : " +dateStr +
                    "\n"+ " Patient Name   : " + billPatientCombo.getSelectedItem()
                    +"\n"+ " Payment Method : "+ payMethod
                    +"\n" +
                    "------------------------------------------------------------------\n"
                    + " Subtotal Bill  : "+ String.format( "%.2f", subtotal )+ " Tk\n"
                    +" VAT / Tax ("+taxPercent+ "%) : +"+String.format("%.2f", taxAmount)+" Tk\n"
                    +" Discount Amount: -" +String.format("%.2f",discount) +" Tk\n"+
                    "------------------------------------------------------------------\n"
                    +
                    " Grand Total    : "
                    +
                    String.format(
                            "%.2f",
                            grandTotal
                    )
                    +
                    " Tk\n"
                    +
                    " Advance Paid   : "
                    +
                    String.format(
                            "%.2f",
                            deposit
                    )
                    +
                    " Tk\n"
                    +
                    "------------------------------------------------------------------\n";


            // -------------------------------------------------
            // CHANGE / AMOUNT DUE
            // -------------------------------------------------

            if (balance >= 0) {

                output +=
                        " Change Due     : "
                        +
                        String.format(
                                "%.2f",
                                balance
                        )
                        +
                        " Tk\n";

            }

            else {

                output +=
                        " Amount Due     : " + String.format( "%.2f", Math.abs( balance
  )
                        )
                        +
                        " Tk (Payable)\n";
            }


            output +=
                    "==================================================================\n";

            receipt.setText(
                    output
            );

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values!"
            );
        }
    });
    return mainPanel;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Swing().setVisible(true));
    }
}
