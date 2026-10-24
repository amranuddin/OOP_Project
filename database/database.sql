CREATE DATABASE IF NOT EXISTS hospital_management;
USE hospital_management;
CREATE TABLE patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT,
    gender VARCHAR(20),
    phone VARCHAR(20),
    address VARCHAR(255),
    blood_group VARCHAR(10),
    symptoms VARCHAR(500)
);
CREATE TABLE doctor (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    chamber_no VARCHAR(20),
    consultation_fee DECIMAL(10,2)
);
CREATE TABLE appointment (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE,
    appointment_time TIME,
    reason VARCHAR(255),
    status VARCHAR(30) DEFAULT 'Scheduled',
    FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id),
    FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
);
CREATE TABLE bed (
    bed_id INT PRIMARY KEY AUTO_INCREMENT,
    bed_number VARCHAR(20),
    room_number VARCHAR(20),
    status VARCHAR(30) DEFAULT 'Available'
);
CREATE TABLE staff_member (
    staff_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    position VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100)
);
CREATE TABLE bill (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    consultation_charge DECIMAL(10,2) DEFAULT 0,
    room_charge DECIMAL(10,2) DEFAULT 0,
    medicine_charge DECIMAL(10,2) DEFAULT 0,
    test_charge DECIMAL(10,2) DEFAULT 0,
    total_amount DECIMAL(10,2) DEFAULT 0,
    payment_status VARCHAR(30) DEFAULT 'Unpaid',
    payment_date DATE,
    FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id)
);