package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Controller.student.DetailStudentController;
import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.DAO.Student_LearnDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Property.CourseProperty;
import com.anhvt.aptechmanagement.Utils.SelectedClassStorage;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClassController extends SideBarController implements Initializable {
    @FXML
    public Button btnInputScore;

    @FXML
    private TableView<Classes> tblClass;
    @FXML
    private TableColumn<Classes, Integer> tcID;
    @FXML
    private TableColumn<Classes, String> tcName;
    @FXML
    private TableColumn<Classes, String> tcSRO;

    @FXML
    private TableView<Student> tblListStudent;
    @FXML
    private TableColumn<Student, Integer> tcIDStudent;
    @FXML
    private TableColumn<Student, String> tcPhone;
    @FXML
    private TableColumn<Student, String> tcStudentName;
    @FXML
    private TableColumn<Student, String> tcStatus;
    @FXML
    private TableColumn<Student, String> tcEmail;



    private Stage scoreStage;
    Classes selectedClass = null;

    ObservableList<Classes> classes;
    ObservableList<Student> students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classes = FXCollections.observableArrayList();
        ArrayList<Classes> list = ClassDAO.getIntance().findAll();
        classes.addAll(list);

        tblClass.setItems(classes);

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcSRO.setCellValueFactory(cellData -> {
            String value = cellData.getValue().getStaff().getFirst_name()
                    + " " + cellData.getValue().getStaff().getLast_name();
            return new SimpleStringProperty(value);
        });

        tblClass.setRowFactory(tv -> {
            TableRow<Classes> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedClass = row.getItem();
                    System.out.println(selectedClass.getName());
                    SelectedClassStorage.setSelectedClass(selectedClass);
                    handle_selectedClass(selectedClass);
                }
            });
            return row;
        });

    }

    private void handle_selectedClass(Classes selectedClass) {
        students = FXCollections.observableList(Student_LearnDAO.getInstance().selectAllStudentsByClass(selectedClass));
        tblListStudent.setItems(students);

        tcIDStudent.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcStudentName.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getLastName() + " " + cellData.getValue().getFirstName();
            return new SimpleStringProperty(name);
        });
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcStatus.setCellValueFactory(cellData -> {
            int value = cellData.getValue().getStatus();
            if(value == 1){
                return new SimpleStringProperty("Theo Học");
            }
            return new SimpleStringProperty("Đã Nghỉ");
        });

    }
    @FXML
    public void gotoInputScore(ActionEvent actionEvent) {
        try {
            if(scoreStage != null && scoreStage.isShowing()){
                scoreStage.toFront();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/score/scoreUI.fxml"));
                Parent root = loader.load();
                scoreStage = new Stage();
                scoreStage.setTitle("Nhập Điểm");
                scoreStage.setScene(new Scene(root));

                scoreStage.setOnCloseRequest(t -> {
                    scoreStage = null;
                });

                ScoreController controller = loader.getController();
                controller.setScoreStage(scoreStage);

                scoreStage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
