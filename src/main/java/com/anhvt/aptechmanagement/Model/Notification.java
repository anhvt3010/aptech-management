package com.anhvt.aptechmanagement.Model;

public class Notification {
    private int id;
    private Student student;
    private String title;
    private String content;
    private Byte status;

    public Notification() {
    }

    public Notification(int id, Student student, String title, String content, Byte status) {
        this.id = id;
        this.student = student;
        this.title = title;
        this.content = content;
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
