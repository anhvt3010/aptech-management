package com.anhvt.aptechmanagement.Model;

public class Classes {
    private int id;
    private Subject subject;
    private Staff staff;
    private String name;
    private String description;
    private int limit;
    private Byte type;

    public Classes(int id, Subject subject, Staff staff, String name, String description, int limit, Byte type) {
        this.id = id;
        this.subject = subject;
        this.staff = staff;
        this.name = name;
        this.description = description;
        this.limit = limit;
        this.type = type;
    }

    public Classes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
