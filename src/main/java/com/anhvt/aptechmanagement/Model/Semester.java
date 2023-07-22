package com.anhvt.aptechmanagement.Model;

public class Semester {
    private int id;
    private Course course;
    private String name;

    public Semester() {
    }

    public Semester(int id, Course course, String name) {
        this.id = id;
        this.course = course;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
