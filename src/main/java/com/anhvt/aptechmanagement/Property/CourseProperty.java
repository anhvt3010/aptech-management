package com.anhvt.aptechmanagement.Property;

import com.anhvt.aptechmanagement.Model.Course;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseProperty {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty semester;
    private SimpleStringProperty status;

    public CourseProperty(Course course) {
        this.id = new SimpleIntegerProperty(course.getId());
        this.name = new SimpleStringProperty(course.getName());
        this.semester = new SimpleIntegerProperty(course.getSemester());
        this.status = new SimpleStringProperty(convertStatusToString(course.getStatus()));
    }

    // Getters and Setters
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


    private String convertStatusToString(Byte status) {
        switch (status){
            case 0:
                return "LOCK";
            case 1:
                return "UNLOCK";
        }
        return "...";
    }
}
