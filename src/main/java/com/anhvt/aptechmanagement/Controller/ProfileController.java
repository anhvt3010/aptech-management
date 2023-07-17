package com.anhvt.aptechmanagement.Controller;


import com.anhvt.aptechmanagement.DAO.ProfileDAO;
import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Navigator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends SideBarController implements Initializable {
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
    private TableColumn<Student, Integer> tcID;

    @FXML
    private TableColumn<Student, String> tcName;

    @FXML
    private TableColumn<Student, String> tcEmail;

    @FXML
    private TableColumn<Student, String> tcPhone;

    @FXML
    private TableColumn<Student, String> tcStatus;

    @FXML
    private TableView<Student> tblListStudent;
    @FXML
    private TableView<Staff> tblListSRO;
    @FXML
    private TableView<Staff> tblListLecturer;


    private ObservableList<Student> listStudent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStudent = FXCollections.observableList(ProfileDAO.getIntance().findAll());

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(celldata -> {
            String name = celldata.getValue().getFirstName() + " " + celldata.getValue(). getLastName();
            return new SimpleStringProperty(name);
        });
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcStatus.setCellValueFactory(cellData -> {
            Byte statusValue = cellData.getValue().getStatus();
            String statusText;
            if (statusValue == 1) {
                statusText = "ACTIVE";
            } else if (statusValue == 2) {
                statusText = "UNACTIVE";
            } else {
                statusText = ""; // Xử lý giá trị không xác định (nếu có)
            }
            return new SimpleStringProperty(statusText);
        });

        tblListStudent.setItems(listStudent);
    }

    @FXML
    void showListLecturer(ActionEvent event) throws IOException {
        Navigator.getInstance().showListLecturer();
    }

    @FXML
    void showListSRO(ActionEvent event) throws IOException {
        Navigator.getInstance().showListSRO();
    }


    @FXML
    void addStudent(ActionEvent event) {

    }

    @FXML
    void detailStudent(ActionEvent event) {

    }





}
