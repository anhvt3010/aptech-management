package com.anhvt.aptechmanagement.Entity;

public class Score {
    private int id;
    private Student student;
    private Subject subject;
    private int score;
    private int percent;
    private int score_max;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getScore_max() {
        return score_max;
    }

    public void setScore_max(int score_max) {
        this.score_max = score_max;
    }
}
