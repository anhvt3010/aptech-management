package com.anhvt.aptechmanagement.Model;

import java.time.LocalDateTime;

public class Schedule {
    private int id;
    private Classes classes;
    private Subject subject;
    private LocalDateTime time_start;
    private LocalDateTime time_end;
    private int num;
    private String Note;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDateTime getTime_start() {
        return time_start;
    }

    public void setTime_start(LocalDateTime time_start) {
        this.time_start = time_start;
    }

    public LocalDateTime getTime_end() {
        return time_end;
    }

    public void setTime_end(LocalDateTime time_end) {
        this.time_end = time_end;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
