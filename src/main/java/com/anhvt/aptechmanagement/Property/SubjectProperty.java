package com.anhvt.aptechmanagement.Property;

import com.anhvt.aptechmanagement.Model.Subject;
import javafx.beans.property.*;

public class SubjectProperty {
    private IntegerProperty id;
    private StringProperty code;
    private StringProperty name;
    private IntegerProperty numberOfSessions;
    private StringProperty description;
    private ObjectProperty<Byte> examFormat;
    private ObjectProperty<Byte> type;

    public SubjectProperty(Subject subject) {
        this.id = new SimpleIntegerProperty(subject.getId());
        this.code = new SimpleStringProperty(subject.getCode());
        this.name = new SimpleStringProperty(subject.getName());
        this.numberOfSessions = new SimpleIntegerProperty(subject.getNumber_of_sessions());
        this.description = new SimpleStringProperty(subject.getDescription());
        this.examFormat = new SimpleObjectProperty<Byte>(subject.getExam_format());
        this.type = new SimpleObjectProperty<Byte>(subject.getType());
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty codeProperty() {
        return code;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty numberOfSessionsProperty() {
        return numberOfSessions;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObjectProperty<Byte> examFormatProperty() {
        return examFormat;
    }

    public ObjectProperty<Byte> typeProperty() {
        return type;
    }
}
