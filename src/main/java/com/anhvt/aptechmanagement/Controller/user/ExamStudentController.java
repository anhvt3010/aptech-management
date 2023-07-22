package com.anhvt.aptechmanagement.Controller.user;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.ExamDAO;
import com.anhvt.aptechmanagement.DAO.Student_LearnDAO;
import com.anhvt.aptechmanagement.Model.Exam;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExamStudentController extends SideBarController implements Initializable {

    @FXML
    private Hyperlink linkHoanThi;

    @FXML
    private Hyperlink linkHocLai;

    @FXML
    private TableView<Exam> tblExam;

    @FXML
    private TableColumn<Exam, String> tcFormat;
    @FXML
    private TableColumn<Exam, LocalDate> tcDate;

    @FXML
    private TableColumn<Exam, String> tcName;

    @FXML
    private TableColumn<Exam, Integer> tcSTT;

    @FXML
    private TableColumn<Exam, String> tcSubject;

    ObservableList<Exam> exams;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHelloStudent.setText("Xin chào " + Session.getAttribute().getLastName());

        Student_Learn studentLearn = Student_LearnDAO.getInstance().selectByStudentID(Session.getAttribute().getId());

        ArrayList<Exam> examArrayList = ExamDAO.getInstance().selectByClassId(studentLearn.getClasses().getId());

        exams = FXCollections.observableList(examArrayList);

        tblExam.setItems(exams);
        tcSTT.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSubject.setCellValueFactory(cellData -> {
            Subject subject = cellData.getValue().getSubject();
            return new SimpleStringProperty(subject.getCode());
        });
        tcFormat.setCellValueFactory(cellData -> {
            Byte formatValue = cellData.getValue().getFormat();
            if(formatValue == 1 ){
                return new SimpleStringProperty("Lý thuyết");
            } else {
                return new SimpleStringProperty("Thực hành");
            }
        });
        tcDate.setCellValueFactory(new PropertyValueFactory<>("exam_day"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
