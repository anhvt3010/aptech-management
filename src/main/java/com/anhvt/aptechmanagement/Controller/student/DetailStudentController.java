package com.anhvt.aptechmanagement.Controller.student;

import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Utils.SelectedIDStorage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailStudentController implements Initializable {
    @FXML
    private RadioButton btnStatus;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton genderFemale;

    @FXML
    private RadioButton genderMale;

    @FXML
    private ComboBox<?> huyen;

    @FXML
    private ComboBox<?> tinh;

    @FXML
    private DatePicker txtBirth;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtlang;

    @FXML
    private ComboBox<?> xa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = StudentDAO.getIntance().selectById(SelectedIDStorage.getSelectedIDStorage());
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());

    }
}
