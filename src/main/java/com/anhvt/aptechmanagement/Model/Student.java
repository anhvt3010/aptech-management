package com.anhvt.aptechmanagement.Model;

import java.sql.Date;
import java.time.LocalDate;


public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private LocalDate birth;
    private String phone;
    private String email;
    private String password;
    private String address;
    private LocalDate created;
    private Byte status;

    public Student(String firstName, String lastName, Boolean gender, LocalDate birth, String phone, String email, String password, String address, LocalDate created, Byte status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.created = created;
        this.status = status;
    }

    public Student(int id, String firstName, String lastName, Boolean gender, LocalDate birth, String phone, String email, String password, String address, LocalDate created, Byte status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.created = created;
        this.status = status;
    }

    public Student() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", created=" + created +
                ", status=" + status +
                '}';
    }
}
