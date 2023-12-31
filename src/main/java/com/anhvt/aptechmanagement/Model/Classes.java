package com.anhvt.aptechmanagement.Model;

import java.time.LocalDate;

public class Classes {
    private int id;
    private Course course;
    private Staff staff;
    private String name;
    private String description;
    private int limit;
    private LocalDate created;
    private Byte type;

    public Classes(int id, Course course, Staff staff, String name, String description, int limit, LocalDate created, Byte type) {
        this.id = id;
        this.course = course;
        this.staff = staff;
        this.name = name;
        this.description = description;
        this.limit = limit;
        this.created = created;
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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", course=" + course +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", limit=" + limit +
                ", type=" + type +
                '}';
    }
}
