package com.anhvt.aptechmanagement.Controller.admin;

import com.anhvt.aptechmanagement.Controller.admin.classes.AddClassController;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.Session;
import com.anhvt.aptechmanagement.Utils.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

import java.io.IOException;

public class SidebarAdminController {

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
    private Button btnNotification;
    @FXML
    private Button btnTest;


    @FXML
    void gotoLogout(ActionEvent event) throws IOException {
        Session.removeAttributeAdmin();
        WindowManager.closeAllStages();
        Navigator.getInstance().gotoLoginAdmin();
    }
    @FXML
    void gotoChangePassword(ActionEvent event) {

    }


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
    void gotoNotification(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoNotificationAdmin();
    }

}
