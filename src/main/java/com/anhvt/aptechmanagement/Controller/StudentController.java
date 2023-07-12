package com.anhvt.aptechmanagement.Controller;


import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.Entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class StudentController extends SideBarController implements Initializable {
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnDetailStudent;
//    @FXML
//    private TableColumn<Student, String> classStudent;

    @FXML
    private TableColumn<Student, String> contact;

//    @FXML
//    private TableColumn<Student, String> course;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> name;

//    @FXML
//    private TableColumn<Student, String> status;

    @FXML
    private TableView<Student> tblListStudent;

    private ObservableList<Student> listStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStudent = FXCollections.observableList(StudentDAO.getIntance().findAll());


        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        contact.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        tblListStudent.setItems(listStudent);
    }

    @FXML
    void addStudent(ActionEvent event) {

    }

    @FXML
    void detailStudent(ActionEvent event) {

    }



}
