package com.anhvt.aptechmanagement.Model;

public class ObjectFromExcelScore {
    private String codeStudent;
    private int score;

    public ObjectFromExcelScore() {
    }

    public ObjectFromExcelScore(String codeStudent, int score) {
        this.codeStudent = codeStudent;
        this.score = score;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ObjectFromExcelScore{" +
                "codeStudent='" + codeStudent + '\'' +
                ", score=" + score +
                '}';
    }
}
