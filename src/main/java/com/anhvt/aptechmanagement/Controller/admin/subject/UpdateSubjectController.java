package com.anhvt.aptechmanagement.Controller.admin.subject;

import com.anhvt.aptechmanagement.Model.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateSubjectController implements Initializable {
    @FXML
    public Button btnQuit;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

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

    private Subject selectedSubject;
    private Stage subjectStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtName.setText(selectedSubject.getName());
        txtCode.setText(selectedSubject.getCode());
        txtNumber.setText(String.valueOf(selectedSubject.getNumber_of_sessions()));
        txtDescription.setText(selectedSubject.getDescription());
        txtFormat.setValue("");
        txtType.setSelected(selectedSubject.getType()==1);
    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    public void setSelectedSubject(Subject selectedSubject) {
        this.selectedSubject = selectedSubject;
    }

    public void setSubjectStage(Stage subjectStage) {
        this.subjectStage = subjectStage;
    }
    @FXML
    public void quit(ActionEvent actionEvent) {
    }
}
