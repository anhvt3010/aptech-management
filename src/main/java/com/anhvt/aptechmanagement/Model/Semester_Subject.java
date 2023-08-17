package com.anhvt.aptechmanagement.Model;

public class Semester_Subject {
    private int id;
    private Semester semester;
    private Subject subject;

    public Semester_Subject() {
    }

    public Semester_Subject(int id, Semester semester, Subject subject) {
        this.id = id;
        this.semester = semester;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Semester_Subject{" +
                "id=" + id +
                ", semester=" + semester +
                ", subject=" + subject +
                '}';
    }
}
