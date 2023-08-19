package com.anhvt.aptechmanagement.Controller.user;

import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

import java.io.IOException;

public class SidebarUserController {
    @FXML
    private MenuItem btnChangePassword;
    @FXML
    private MenuItem btnLogout;

    @FXML
    private Button btnUserScore;
    @FXML
    private Button btnUserDocument;
    @FXML
    private Button btnUserNotification;
    @FXML
    private Button btnUserSchedule;
    @FXML
    private Button btnUserStudent;
    @FXML
    private Button btnUserTest;

    @FXML
    public Text txtHelloStudent;

    @FXML
    void gotoLogout(ActionEvent event) throws IOException {
        Session.removeAttribute();
        Navigator.getInstance().gotoLoginStudent();
    }
    @FXML
    void gotoChangePassword(ActionEvent event) {

    }
    @FXML
    void gotoUserScore(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScore();
    }

    @FXML
    void gotoUserDocument(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoDocumentUser();
    }

    @FXML
    void gotoUserNotification(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoNotification();
    }

    @FXML
    void gotoUserSchedule(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScheduleUser();
    }

    @FXML
    void gotoUserStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoUserStudent();
    }

    @FXML
    void gotoUserTest(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoTestUser();
    }

}
