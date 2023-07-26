package com.anhvt.aptechmanagement.Model;

public class Subject {
    private int id;
    private String code;
    private String name;
    private int number_of_sessions;
    private String description;
    private Byte exam_format;
    private Byte type;

    public Subject(int id, String code, String name, int number_of_sessions, String description, Byte exam_format, Byte type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.number_of_sessions = number_of_sessions;
        this.description = description;
        this.exam_format = exam_format;
        this.type = type;
    }

    public Subject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_sessions() {
        return number_of_sessions;
    }

    public void setNumber_of_sessions(int number_of_sessions) {
        this.number_of_sessions = number_of_sessions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getExam_format() {
        return exam_format;
    }

    public void setExam_format(Byte exam_format) {
        this.exam_format = exam_format;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", Code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", number_of_sessions=" + number_of_sessions +
                ", description='" + description + '\'' +
                ", exam_format=" + exam_format +
                ", type=" + type +
                '}';
    }
}
