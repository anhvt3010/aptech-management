package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController {

    @FXML
    private MenuItem btnChangePassword;
    @FXML
    private MenuItem btnLogout;

    @FXML
    private Text txtHelloAdmin;


    //  ------------ Button Admin -----------------
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
    private Button btnNoti;
    @FXML
    private Button btnTest;


    //  ------------ Button User -----------------
    @FXML
    private Button btnScore;
    @FXML
    private Button btnUserDocument;
    @FXML
    private Button btnUserNoti;
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

    //  ------------ Action Admin -----------------

    @FXML
    void gotoClass(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoClass();
    }

    @FXML
    void gotoCourse(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoCourse();
    }

    @FXML
    void gotoDocument(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoDocument();
    }

    @FXML
    void gotoSchedule(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoSchedule();
    }

    @FXML
    void gotoStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoStudent();
    }

    @FXML
    void gotoTest(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoTest();
    }
    @FXML
    void gotoNoti(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoNotification();
    }

    //  ------------ Action User -----------------

    @FXML
    void gotoScore(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScore();
    }

    @FXML
    void gotoUserDocument(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoDocumentUser();
    }

    @FXML
    void gotoUserNoti(ActionEvent event) throws IOException {
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
