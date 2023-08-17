package com.anhvt.aptechmanagement.Controller.admin.semester;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSemesterController implements Initializable {
    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtCode;

    @FXML
    private TextArea txtDescription;

    @FXML
    private ChoiceBox<String> txtFormat;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private CheckBox txtType;

    @FXML
    void quit(ActionEvent event) {

    }

    @FXML
    void saveSubject(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
