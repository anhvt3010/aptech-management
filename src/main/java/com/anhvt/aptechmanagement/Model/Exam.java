package com.anhvt.aptechmanagement.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exam {
    private int id;
    private Subject subject;
    private Classes classes;
    private String name;
    private LocalDate exam_day;
    private Byte format;
    private Byte status;

    public Exam() {
    }

    public Exam(int id, Subject subject, Classes classes, String name, LocalDate exam_day, Byte format, Byte status) {
        this.id = id;
        this.subject = subject;
        this.classes = classes;
        this.name = name;
        this.exam_day = exam_day;
        this.format = format;
        this.status = status;
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getFormat() {
        return format;
    }

    public void setFormat(Byte format) {
        this.format = format;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDate getExam_day() {
        return exam_day;
    }

    public void setExam_day(LocalDate exam_day) {
        this.exam_day = exam_day;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", subject=" + subject +
                ", classes=" + classes +
                ", name='" + name + '\'' +
                ", exam_day=" + exam_day +
                ", format=" + format +
                ", status=" + status +
                '}';
    }
}
