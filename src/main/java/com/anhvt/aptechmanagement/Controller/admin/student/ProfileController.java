package com.anhvt.aptechmanagement.Controller.admin.student;


import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.Controller.admin.student.AddStudentController;
import com.anhvt.aptechmanagement.Controller.admin.student.DetailStudentController;
import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.DAO.Student_LearnDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Navigator;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController extends SideBarController implements Initializable {
    @FXML
    public MenuItem btnListSRO;
    @FXML
    public MenuItem btnListLecturer;
    @FXML
    public ChoiceBox<String> choiceClass;

    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnDetailStudent;

    @FXML
    private TableColumn<Student, Integer> tcID;

    @FXML
    private TableColumn<Student, String> tcName;

    @FXML
    private TableColumn<Student, String> tcCodeStudent;

    @FXML
    private TableColumn<Student, String> tcEmail;

    @FXML
    private TableColumn<Student, String> tcPhone;

    @FXML
    private TableColumn<Student, String> tcStatus;

    @FXML
    private TableView<Student> tblListStudent;

    private Stage studentDetailStage;
    private Stage studentAddStage;

    Classes selectedClass;

    ObservableList<Student> listStudent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listStudent = FXCollections.observableList(StudentDAO.getInstance().findAll());

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(celldata -> {
            String name = celldata.getValue().getFirstName() + " " + celldata.getValue(). getLastName();

            return new SimpleStringProperty(name);
        });
        tcCodeStudent.setCellValueFactory(cellData -> {
            int id =  cellData.getValue().getId();
            String code = "";
            try {
                code = Student_LearnDAO.getInstance().selectByStudentID(id).getStudent_code();
            } catch (Exception e) {
                code = "N/A";
            }
            return new SimpleStringProperty(code);
        });
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcStatus.setCellValueFactory(cellData -> {
            Byte statusValue = cellData.getValue().getStatus();
            String statusText;
            if (statusValue == 1) {
                statusText = "Hoạt Động";
            } else {
                statusText = "Đã Nghỉ";
            }
            return new SimpleStringProperty(statusText);
        });

        tblListStudent.setItems(listStudent);

        btnDetailStudent.setDisable(true);

        tblListStudent.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    SelectedIDStorage.setSelectedIDStorage(row.getItem().getId());
                    System.out.println(SelectedIDStorage.getSelectedIDStorage());
                    btnDetailStudent.setDisable(false);
                }
            });

            return row;
        });

        this.showListCass();

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
        try {
            if(studentAddStage != null && studentAddStage.isShowing()){
                studentAddStage.toFront();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/profile/student/addStudentUI.fxml"));
                Parent root = loader.load();
                studentAddStage = new Stage();
                studentAddStage.setTitle("Thêm học viên");
                studentAddStage.setScene(new Scene(root));

                studentAddStage.setOnCloseRequest(t -> {
                    studentAddStage = null;
                });

                AddStudentController controller = loader.getController();
                controller.setStage(studentAddStage);

                studentAddStage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void detailStudent(ActionEvent event) {
        try {
            if(studentDetailStage != null && studentDetailStage.isShowing()){
                studentDetailStage.toFront();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/profile/student/detailStudentUI.fxml"));
                Parent root = loader.load();
                studentDetailStage = new Stage();
                studentDetailStage.setTitle("Chi tiết học viên");
                studentDetailStage.setScene(new Scene(root));

                studentDetailStage.setOnCloseRequest(t -> {
                    studentDetailStage = null;
                });

                DetailStudentController controller = loader.getController();
                controller.setDetailStudentStage(studentDetailStage);

                studentDetailStage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListCass(){
        ArrayList<Classes> classes = ClassDAO.getIntance().findAll();
        ArrayList<String> listClass = new ArrayList<>();

        for (Classes cls : classes) {
            listClass.add(cls.getName());
        }

        choiceClass.setOnAction(event -> {
            int selectedIndex = choiceClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                selectedClass = classes.get(selectedIndex);
                listStudent.setAll(Student_LearnDAO.getInstance().selectAllStudentsByClass(selectedClass));
                System.out.println("Chọn class name: " + classes.get(selectedIndex).getName());
            }
        });
        choiceClass.getItems().addAll(listClass);
    }

}
