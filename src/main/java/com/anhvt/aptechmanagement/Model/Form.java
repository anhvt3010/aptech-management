package com.anhvt.aptechmanagement.Model;

import java.time.LocalDate;

public class Form {
    private int id;
    private String title;
    private String link;
    private LocalDate created;
    private Byte status;

    public Form() {
    }

    public Form(int id, String title, String link, LocalDate created, Byte status) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.created = created;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", created=" + created +
                ", status=" + status +
                '}';
    }
}
