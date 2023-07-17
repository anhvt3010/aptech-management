package com.anhvt.aptechmanagement.Model;

public class Subject {
    private int id;
    private String Code;
    private String name;
    private String number_of_sessions;
    private String objectives_achieved;
    private Byte exam_format;
    private Byte type;

    public Subject(int id, String code, String name, String number_of_sessions, String objectives_achieved, Byte exam_format, Byte type) {
        this.id = id;
        Code = code;
        this.name = name;
        this.number_of_sessions = number_of_sessions;
        this.objectives_achieved = objectives_achieved;
        this.exam_format = exam_format;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_of_sessions() {
        return number_of_sessions;
    }

    public void setNumber_of_sessions(String number_of_sessions) {
        this.number_of_sessions = number_of_sessions;
    }

    public String getObjectives_achieved() {
        return objectives_achieved;
    }

    public void setObjectives_achieved(String objectives_achieved) {
        this.objectives_achieved = objectives_achieved;
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
}
