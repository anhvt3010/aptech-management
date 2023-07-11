package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectController {

    @FXML
    private Button btnIsAdmin;

    @FXML
    private Button btnIsStudent;

//    private Stage previousStage;

    @FXML
    private void loginAsAdmin(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoLoginAdmin();
    }

    @FXML
    private void loginAsStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoLoginStudent();
    }
}
