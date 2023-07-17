package com.anhvt.aptechmanagement.Property;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseProperty {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty semester;
    private SimpleStringProperty status;

    public CourseProperty(SimpleIntegerProperty id, SimpleStringProperty name, SimpleIntegerProperty semester, SimpleStringProperty status) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.status = status;
    }

    public CourseProperty() {

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getSemester() {
        return semester.get();
    }

    public SimpleIntegerProperty semesterProperty() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester.set(semester);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
