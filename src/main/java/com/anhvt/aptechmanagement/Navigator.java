package com.anhvt.aptechmanagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {

    private static Navigator navigator;
    private Stage stage;
    private Stage previousStage;

    public static final String SELECT = "UI/select.fxml";
    public static final String ADMIN_HOME = "UI/admin/index.fxml";
    public static final String LOGIN_ADMIN = "UI/loginAdminUI.fxml";
    public static final String LOGIN_STUDENT= "UI/loginStudentUI.fxml";
    public static final String STUDENT_HOME = "UI/user/index.fxml";
    public static final String STUDENT_MANAGER = "UI/admin/student/list.fxml";
    public static final String BROWSER = "UI/user/browser.fxml";

 // URL side bar Admin
    public static final String SIDE_BAR_STUDENT_ADMIN = "UI/admin/student/listStudentUI.fxml";
    public static final String SIDE_BAR_COURSE_ADMIN = "UI/admin/course/listCourseUI.fxml";
    public static final String SIDE_BAR_CLASS_ADMIN = "UI/admin/course/listClassUI.fxml";
    public static final String SIDE_BAR_SCHEDULE_ADMIN = "UI/admin/course/listScheduleUI.fxml";
    public static final String SIDE_BAR_TEST_ADMIN = "UI/admin/course/listTestUI.fxml";
    public static final String SIDE_BAR_DOCUMENT_ADMIN = "UI/admin/course/listDocumentUI.fxml";



    private Navigator(){}

    public static Navigator getInstance(){
        if (navigator == null){
            navigator = new Navigator();
        }
        return navigator;
    }

    public void setStage(Stage stage){
        navigator.stage = stage;
    }

    public void gotoScene(String title, String URL) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(URL)));
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        if(!stage.isShowing()){
            stage.show();
        }
    }

    public void gotoSelect() throws IOException {
        gotoScene("select", SELECT);
    }
    public void gotoLoginAdmin() throws IOException {
        gotoScene("Đăng nhập với tư cách quản li", LOGIN_ADMIN);
    }
    public void gotoLoginStudent() throws IOException {
        gotoScene("Đăng nhập với tư cách học viên", LOGIN_STUDENT);
    }

    public void gotoAdminHome() throws IOException {
        gotoScene("Admin Home", ADMIN_HOME);
    }

    public void gotoStudentHome() throws IOException {
        gotoScene("Student Home", STUDENT_HOME);
    }
    public void gotoBrowser() {
        try {
            gotoScene("Trình duyệt nhanh", BROWSER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gotoStudentManager() throws IOException {
        gotoScene("Quản lí học viên", STUDENT_MANAGER);
    }

    // Go to view Side Bar
    public void gotoStudent() throws IOException {
        gotoScene("Quản lí học viên", SIDE_BAR_STUDENT_ADMIN);
    }
    public void gotoCourse() throws IOException {
        gotoScene("Quản lí khóa học", SIDE_BAR_COURSE_ADMIN);
    }
    public void gotoClass() throws IOException {
        gotoScene("Quản lí lớp học", SIDE_BAR_CLASS_ADMIN);
    }

    public void gotoSchedule() throws IOException {
        gotoScene("Quản lí lịch học", SIDE_BAR_CLASS_ADMIN);
    }
    public void gotoTest() throws IOException {
        gotoScene("Quản lí lịch thi", SIDE_BAR_TEST_ADMIN);
    }
    public void gotoDocument() throws IOException {
        gotoScene("Quản lí đơn từ", SIDE_BAR_DOCUMENT_ADMIN);
    }

}
