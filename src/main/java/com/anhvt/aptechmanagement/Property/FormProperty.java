package com.anhvt.aptechmanagement.Property;

import com.anhvt.aptechmanagement.Model.Form;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class FormProperty {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty link;
    private ObjectProperty<LocalDate> created;
    private SimpleStringProperty status;

    public FormProperty() {
    }

    public FormProperty(Form form) {
        this.id = new SimpleIntegerProperty(form.getId());
        this.title = new SimpleStringProperty(form.getTitle());
        this.link = new SimpleStringProperty(form.getLink());
        this.created =  new SimpleObjectProperty<>(form.getCreated());
        this.status = new SimpleStringProperty(convertToStringStatus(form.getStatus()));
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

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getLink() {
        return link.get();
    }

    public SimpleStringProperty linkProperty() {
        return link;
    }

    public void setLink(String link) {
        this.link.set(link);
    }

    public LocalDate getCreated() {
        return created.get();
    }

    public ObjectProperty<LocalDate> createdProperty() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created.set(created);
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
    public String convertToStringStatus(Byte value){
        return switch (value) {
            case 0 -> "Không";
            case 1 -> "Đang HĐ";
            default -> "N/A";
        };
    }
}
