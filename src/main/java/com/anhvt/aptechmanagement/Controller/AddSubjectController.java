package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.SubjectDAO;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Validation.EmailValidator;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import com.anhvt.aptechmanagement.Validation.PhoneNumberValidator;
import com.anhvt.aptechmanagement.Validation.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {
    private Stage stage;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtCode;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private CheckBox txtType;
    @FXML
    private ChoiceBox<String> txtFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSave.setDisable(true);
        this.setPlaceholderSubject();

        Map<Integer, String> mapFormat = new HashMap<>();
        mapFormat.put(0, "Lý Thuyết");
        mapFormat.put(1, "Lý Thuyết và Thực Hành");
        mapFormat.put(2, "Đồ Án");
        txtFormat.getItems().addAll(mapFormat.values());

        txtName.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtCode.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtNumber.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtDescription.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtFormat.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());


    }
    @FXML
    void saveSubject(ActionEvent event) throws IOException {
        SubjectDAO.getIntance().insert(this.getObjectSubject());
        Navigator.getInstance().gotoListSubject();
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
        AlertUtil.showInforEAlert("Thông Báo", "Thêm môn học thành công", "");
    }
    public Subject getObjectSubject(){
        Subject subject = new Subject();
        subject.setCode(txtCode.getText());
        subject.setName(txtName.getText());
        subject.setNumber_of_sessions(Integer.parseInt(txtNumber.getText()));
        subject.setDescription(txtDescription.getText());

        byte format;
        if(txtFormat.getValue().equals("Lý Thuyết")){
            format = (byte) 0;
        } else if(txtFormat.getValue().equals("Lý Thuyết và Thực Hành")){
            format = (byte) 1;
        } else {
            format = (byte) 2;
        }
        subject.setExam_format(format);

        boolean typeSelected = txtType.isSelected();
        byte type = typeSelected ? (byte) 1 : (byte) 0;
        subject.setType(type);

        return subject;
    }

    private void updateSaveButtonStatus() {
        boolean allFieldsFilled = !txtCode.getText().isEmpty() &&
                !txtName.getText().isEmpty() &&
                !txtNumber.getText().isEmpty() &&
                !txtDescription.getText().isEmpty() &&
                txtFormat.getValue() != null &&
                InputTextValidator.isPositiveInteger(txtNumber.getText(), txtNumber);
        btnSave.setDisable(!allFieldsFilled);
    }

    public void setPlaceholderSubject() {
        if (txtCode.getText().isEmpty()) {
            txtCode.setPromptText("Nhập mã môn học...");
        }
        if (txtName.getText().isEmpty()) {
            txtName.setPromptText("Nhập tên môn học...");
        }
        if (txtNumber.getText().isEmpty()) {
            txtNumber.setPromptText("Nhập số giờ học...");
        }
        if (txtDescription.getText().isEmpty()) {
            txtDescription.setPromptText("Nhập mục tiêu môn học...");
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void quit(ActionEvent event) {
        stage.close();
    }
}
