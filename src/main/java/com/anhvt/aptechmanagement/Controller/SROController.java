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

public class SROController extends SideBarController implements Initializable {


    @FXML
    private MenuItem btnListLecturer;

    @FXML
    private MenuItem btnListStudent;


    @FXML
    private TableView<Staff> tblListSRO;

    @FXML
    private TableColumn<Staff, Integer> tcID;

    @FXML
    private TableColumn<Staff, String> tcName;

    @FXML
    private TableColumn<Staff, String> tcEmail;

    @FXML
    private TableColumn<Staff, String> tcPhone;

    @FXML
    private TableColumn<Staff, String> tcStatus;

    private ObservableList<Staff> listSRO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listSRO = FXCollections.observableList(ProfileDAO.getIntance().findAllSRO());

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(celldata -> {
            String name = celldata.getValue().getFirst_name() + " " + celldata.getValue().getLast_name();
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

        tblListSRO.setItems(listSRO);
    }
    @FXML
    void showListStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().showListStudent();
    }

    @FXML
    void showListLecturer(ActionEvent event) throws IOException {
        Navigator.getInstance().showListLecturer();
    }

    public void addSRO(ActionEvent actionEvent) {
    }

    public void detailSRO(ActionEvent actionEvent) {
    }
}
