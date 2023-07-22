package com.anhvt.aptechmanagement.Controller;


import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.SelectedIDStorage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    private boolean isAddStudentWindowOpen = false;
    private Stage studentStage;

    private ObservableList<Student> listStudent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStudent = FXCollections.observableList(StudentDAO.getIntance().findAll());

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
                statusText = "";
            }
            return new SimpleStringProperty(statusText);
        });

        tblListStudent.setItems(listStudent);

        tblListStudent.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    SelectedIDStorage.setSelectedIDStorage(row.getItem().getId());

                    System.out.println(SelectedIDStorage.getSelectedIDStorage());
                }
            });

            return row;
        });
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
    void addStudent(ActionEvent event) throws IOException {
        if (isAddStudentWindowOpen) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/profile/student/addStudentUI.fxml"));
            Parent root = loader.load();

            studentStage = new Stage();
            studentStage.setTitle("Thêm học viên");
            studentStage.setScene(new Scene(root));

            studentStage.setOnCloseRequest(t -> {
                isAddStudentWindowOpen = false;
                studentStage = null;
            });

            studentStage.show();

            isAddStudentWindowOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void detailStudent(ActionEvent event) {
        if (isAddStudentWindowOpen) {
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/profile/student/detailStudentUI.fxml"));
            Parent root = loader.load();

            studentStage = new Stage();
            studentStage.setTitle("Chi tiết học viên");
            studentStage.setScene(new Scene(root));

            studentStage.setOnCloseRequest(t -> {
                isAddStudentWindowOpen = false;
                studentStage = null;
            });

            studentStage.show();

            isAddStudentWindowOpen = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
