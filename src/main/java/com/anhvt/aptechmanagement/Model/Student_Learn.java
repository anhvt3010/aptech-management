package com.anhvt.aptechmanagement.Model;

public class Student_Learn {
    private int id;
    private Student student;
    private Classes classes;
    private Course course;

    public Student_Learn() {
    }

    public Student_Learn(int id, Student student, Classes classes, Course course) {
        this.id = id;
        this.student = student;
        this.classes = classes;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
