package com.anhvt.aptechmanagement.Property;

import com.anhvt.aptechmanagement.Model.Notification;
import com.anhvt.aptechmanagement.Model.Student;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class NotificationProperty {
    private SimpleIntegerProperty id;
    private ObjectProperty<Student> student;
    private SimpleStringProperty title;
    private SimpleStringProperty content;
    private SimpleStringProperty status;

    public NotificationProperty() {
    }

    public NotificationProperty(Notification notification) {
        this.id = new SimpleIntegerProperty(notification.getId());
        this.student = new SimpleObjectProperty<>(notification.getStudent());
        this.title = new SimpleStringProperty(notification.getTitle());
        this.content = new SimpleStringProperty(notification.getContent());
        this.status = new SimpleStringProperty(convertStatusToString(notification.getStatus()));
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

    public Student getStudent() {
        return student.get();
    }

    public ObjectProperty<Student> studentProperty() {
        return student;
    }

    public void setStudent(Student student) {
        this.student.set(student);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
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

    String convertStatusToString(int value){
        if (value == 0) {
            return "Hiển Thị";
        } else {
            return "Đã Ẩn";
        }
    }
}
