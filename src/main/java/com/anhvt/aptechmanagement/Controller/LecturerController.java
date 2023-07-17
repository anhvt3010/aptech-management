package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.ProfileDAO;
import com.anhvt.aptechmanagement.Model.Staff;
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

public class LecturerController extends SideBarController implements Initializable {
    @FXML
    private Button btnAddLecturer;

    @FXML
    private Button btnDetailLecturer;

    @FXML
    private MenuItem btnListSRO;

    @FXML
    private MenuItem btnListStudent;

    @FXML
    private TableView<Staff> tblListLecturer;


    @FXML
    private TableColumn<Staff, String> tcEmail;

    @FXML
    private TableColumn<Staff, Integer> tcID;

    @FXML
    private TableColumn<Staff, String> tcName;

    @FXML
    private TableColumn<Staff, String> tcPhone;

    @FXML
    private TableColumn<Staff, String> tcStatus;

    ObservableList<Staff> listStaffs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStaffs = FXCollections.observableList(ProfileDAO.getIntance().findAllLecturer());

        tblListLecturer.setItems(listStaffs);

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(celldata -> {
            String name = celldata.getValue().getLast_name()+ "  " +celldata.getValue().getFirst_name();
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


    }
    @FXML
    public void addLecturer(ActionEvent actionEvent) {
    }

    @FXML
    void showListStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().showListStudent();
    }

    @FXML
    void showListSRO(ActionEvent event) throws IOException {
        Navigator.getInstance().showListSRO();
    }
    @FXML
    public void detailLecturer(ActionEvent actionEvent) {
    }
}
