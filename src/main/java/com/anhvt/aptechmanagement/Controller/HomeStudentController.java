package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class HomeStudentController {
    @FXML
    private MenuItem btnChangePassword;

    @FXML
    private Button btnDocument;

    @FXML
    private Button btnInfor;

    @FXML
    private MenuItem btnLogout;

    @FXML
    private Button btnNoty;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnScore;

    @FXML
    private Button btnTest;

    @FXML
    void changePassword(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void showDocument(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoAdminHome();

    }

    @FXML
    void showInfor(ActionEvent event) {

    }

    @FXML
    void showNoty(ActionEvent event) {

    }

    @FXML
    void showSchedule(ActionEvent event) {

    }

    @FXML
    void showScore(ActionEvent event) {

    }

    @FXML
    void showTest(ActionEvent event) {

    }
}
