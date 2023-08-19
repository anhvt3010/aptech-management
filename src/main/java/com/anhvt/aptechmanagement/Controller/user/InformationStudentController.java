package com.anhvt.aptechmanagement.Controller.user;

import com.anhvt.aptechmanagement.DAO.Student_LearnDAO;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationStudentController extends SidebarUserController implements Initializable {
    @FXML
    private TextArea txtStudentAddress;

    @FXML
    private TextField txtStudentBirth;

    @FXML
    private TextField txtStudentClass;

    @FXML
    private TextField txtStudentCourse;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentGender;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtStudentName.setText(Session.getAttribute().getFirstName() + Session.getAttribute().getLastName());
        txtStudentGender.setText(Session.getAttribute().getGender()?"Nam":"Nữ");
        txtStudentBirth.setText(Session.getAttribute().getBirth().toString());
        txtStudentBirth.setText(Session.getAttribute().getBirth().toString());
        txtStudentAddress.setText(Session.getAttribute().getAddress());
        txtStudentPhone.setText(Session.getAttribute().getPhone());
        txtStudentEmail.setText(Session.getAttribute().getEmail());

        txtStudentClass.setText(Student_LearnDAO.getInstance().selectByStudentID(Session.getAttribute().getId()).getClasses().getName());
        txtStudentCourse.setText(Student_LearnDAO.getInstance().selectByStudentID(Session.getAttribute().getId()).getCourse().getName());
    }
}
