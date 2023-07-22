package com.anhvt.aptechmanagement.Model;

import java.time.LocalDateTime;

public class Schedule {
    private int id;
    private Classes classes;

    private Semester semester;
    private String link;

    public Schedule() {
    }

    public Schedule(int id, Classes classes, Semester semester, String link) {
        this.id = id;
        this.classes = classes;
        this.semester = semester;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
