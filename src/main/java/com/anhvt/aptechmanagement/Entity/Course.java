package com.anhvt.aptechmanagement.Entity;

public class Course {
    private int id;
    private String name;
    private int semester;
    private Byte status;



    public Course(int id, String name, int semester, Byte status) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.status = status;
    }

    public Course() {

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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
