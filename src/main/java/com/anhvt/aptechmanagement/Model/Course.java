package com.anhvt.aptechmanagement.Model;

public class Course {
    private int id;
    private String name;
    private int semester;
    private Byte status;

//    private IntegerProperty idProperty;
//    private StringProperty nameProperty;
//    private IntegerProperty semProperty;
//    private StringProperty statusProperty;
//
//    public static final int STATUS_LOCK = 1;
//    public static final int STATUS_UNLOCK = 2;

    public Course(int id, String name, int semester, Byte status) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.status = status;

//        this.idProperty = new SimpleIntegerProperty(id);
//        this.nameProperty = new SimpleStringProperty(name);
//        this.semProperty = new SimpleIntegerProperty(semester);
//        switch (status){
//            case STATUS_LOCK:
//                this.statusProperty = new SimpleStringProperty("LOCK");
//                break;
//            case STATUS_UNLOCK:
//                this.statusProperty = new SimpleStringProperty("UNLOCK");
//                break;
//        }

    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
//        this.idProperty.set(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
//        this.nameProperty.set(name);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
//        this.semProperty.set(semester);
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
//        switch (status){
//            case STATUS_LOCK:
//                this.statusProperty.set("LOCK");
//                break;
//            case STATUS_UNLOCK:
//                this.statusProperty.set("UNLOCK");
//                break;
//        }
    }
//    public Integer getIdProperty() {
//        return idProperty.get();
//    }
//
//    public IntegerProperty idPropertyProperty() {
//        return idProperty;
//    }
//
//    public String getNameProperty() {
//        return nameProperty.get();
//    }
//
//    public StringProperty namePropertyProperty() {
//        return nameProperty;
//    }
//
//    public int getSemProperty() {
//        return semProperty.get();
//    }
//
//    public IntegerProperty semPropertyProperty() {
//        return semProperty;
//    }
//
//    public String getStatusProperty() {
//        return statusProperty.get();
//    }
//
//    public StringProperty statusPropertyProperty() {
//        return statusProperty;
//    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", status=" + status +
                '}';
    }
}
