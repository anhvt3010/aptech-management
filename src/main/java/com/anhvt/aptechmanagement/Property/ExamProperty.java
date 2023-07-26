package com.anhvt.aptechmanagement.Property;

import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Exam;
import com.anhvt.aptechmanagement.Model.Subject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ExamProperty {
    private SimpleIntegerProperty id;
    private ObjectProperty<Subject> subject;
    private ObjectProperty<Classes> classes;
    private SimpleStringProperty name;
    private ObjectProperty<LocalDate> exam_day;
    private SimpleStringProperty format;
    private SimpleStringProperty status;

    public ExamProperty(Exam exam) {
        this.id = new SimpleIntegerProperty(exam.getId());
        this.subject = new SimpleObjectProperty<>(exam.getSubject());
        this.classes = new SimpleObjectProperty<>(exam.getClasses());
        this.name = new SimpleStringProperty(exam.getName());
        this.exam_day = new SimpleObjectProperty<>(exam.getExam_day());
        this.format = new SimpleStringProperty(convertToStringExamFormat(exam.getFormat()));
        this.status = new SimpleStringProperty(convertToStringStatus(exam.getFormat()));
    }

    public ExamProperty() {
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

    public Subject getSubject() {
        return subject.get();
    }

    public ObjectProperty<Subject> subjectProperty() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject.set(subject);
    }

    public Classes getClasses() {
        return classes.get();
    }

    public ObjectProperty<Classes> classesProperty() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes.set(classes);
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

    public LocalDate getExam_day() {
        return exam_day.get();
    }

    public ObjectProperty<LocalDate> exam_dayProperty() {
        return exam_day;
    }

    public void setExam_day(LocalDate exam_day) {
        this.exam_day.set(exam_day);
    }

    public String getFormat() {
        return format.get();
    }

    public SimpleStringProperty formatProperty() {
        return format;
    }

    public void setFormat(String format) {
        this.format.set(format);
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
    public String convertToStringExamFormat(Byte value){
        return switch (value) {
            case 0 -> "Lý Thuyết";
            case 1 -> "Thực Hành";
            default -> "N/A";
        };
    }
    public String convertToStringStatus(Byte value){
        return switch (value) {
            case 0 -> "Chưa Thi";
            case 1 -> "Đã Thi";
            default -> "N/A";
        };
    }
}
