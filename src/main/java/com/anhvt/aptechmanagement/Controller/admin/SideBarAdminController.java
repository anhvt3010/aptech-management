package com.anhvt.aptechmanagement.Controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class SideBarAdminController {
    @FXML
    public AnchorPane content;
    @FXML
    private MenuItem btnChangePassword;
    @FXML
    private MenuItem btnLogout;

    @FXML
    private Button btnClass;
    @FXML
    private Button btnCourse;
    @FXML
    private Button btnDocument;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnStudent;
    @FXML
    private Button btnNotification;
    @FXML
    private Button btnTest;

    @FXML
    private Text txtHelloAdmin;

    @FXML
    private SplitPane wrapperPane;

    public static final String SIDE_BAR_STUDENT_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/profile/student/listStudentUI.fxml";
    public static final String SIDE_BAR_COURSE_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/course/listCourseUI.fxml";
    public static final String SIDE_BAR_CLASS_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/class/listClassUI.fxml";
    public static final String SIDE_BAR_SCHEDULE_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/schedule/listScheduleUI.fxml";
    public static final String SIDE_BAR_TEST_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/test/listTestUI.fxml";
    public static final String SIDE_BAR_FORM_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/form/listFormUI.fxml";
    public static final String SIDE_BAR_NOTIFICATION_ADMIN = "/com/anhvt/aptechmanagement/UIv2/admin/notification/listNotificationUI.fxml";

    @FXML
    void gotoLogout(ActionEvent event) throws IOException {
        // Handle logout action
    }

    @FXML
    void gotoChangePassword(ActionEvent event) {
        // Handle change password action
    }

    @FXML
    void navigateToView(String viewPath) throws IOException {
        content.getChildren().clear();
        SplitPane contentPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(viewPath)));
        SplitPane wrapperPane = new SplitPane(contentPane);
        content.getChildren().add(wrapperPane);
    }

    @FXML
    void gotoClass(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnClass.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_CLASS_ADMIN);
    }

    @FXML
    void gotoCourse(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnCourse.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_COURSE_ADMIN);
    }

    @FXML
    void gotoForm(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnDocument.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_FORM_ADMIN);
    }

    @FXML
    void gotoSchedule(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnSchedule.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_SCHEDULE_ADMIN);
    }

    @FXML
    void gotoStudent(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnStudent.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_STUDENT_ADMIN);
    }

    @FXML
    void gotoTest(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnTest.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_TEST_ADMIN);
    }

    @FXML
    void gotoNotification(ActionEvent event) throws IOException {
        this.AllButtonWhite();
        btnNotification.setStyle("-fx-background-color: #6E8199;");
        navigateToView(SIDE_BAR_NOTIFICATION_ADMIN);
    }

    private void AllButtonWhite(){
        btnStudent.setStyle("-fx-background-color: #FFF;");
        btnClass.setStyle("-fx-background-color: #FFF;");
        btnCourse.setStyle("-fx-background-color: #FFF;");
        btnDocument.setStyle("-fx-background-color: #FFF;");
        btnNotification.setStyle("-fx-background-color: #FFF;");
        btnSchedule.setStyle("-fx-background-color: #FFF;");
        btnTest.setStyle("-fx-background-color: #FFF;");
    }
}