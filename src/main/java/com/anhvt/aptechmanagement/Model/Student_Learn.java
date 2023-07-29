package com.anhvt.aptechmanagement.Model;

public class Student_Learn {
    private int id;
    private Student student;
    private Classes classes;
    private Course course;
    private String student_code;

    public Student_Learn() {
    }

    public Student_Learn(int id, Student student, Classes classes, Course course, String student_code) {
        this.id = id;
        this.student = student;
        this.classes = classes;
        this.course = course;
        this.student_code = student_code;
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

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    @Override
    public String toString() {
        return "Student_Learn{" +
                "id=" + id +
                ", student=" + student +
                ", classes=" + classes +
                ", course=" + course +
                '}';
    }
}
