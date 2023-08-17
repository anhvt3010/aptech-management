package com.anhvt.aptechmanagement.Controller.admin.exam;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Exam;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddExamController implements Initializable {
    private Stage examStage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnQuit;

    @FXML
    private ChoiceBox<String> txtAddClass;

    @FXML
    private ChoiceBox<String> txtAddFormat;

    @FXML
    private TextArea txtAddName;

    @FXML
    private ChoiceBox<String> txtAddSubject;

    @FXML
    private DatePicker txtDate;

    private Classes selectedClass = null;
    private Subject selectedSubject = null;
    private int selectedFormat;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showListClasses();
        this.showTxtStatus();
    }

    @FXML
    void addNewExam(ActionEvent event) throws IOException {
         Exam exam = new Exam();
         exam.setClasses(selectedClass);
         exam.setSubject(selectedSubject);
         exam.setExam_day(txtDate.getValue());
         exam.setName(txtAddName.getText());
         exam.setFormat((byte) selectedFormat);
         exam.setStatus((byte) 0);

         ExamDAO.getInstance().insert(exam);
         System.out.println("Thêm thành công");
         Navigator.getInstance().gotoTest();
         examStage.close();
    }

    public void showListClasses(){
        ArrayList<Classes> classes = ClassDAO.getIntance().findAll();
        ArrayList<String> listClasses = new ArrayList<>();

        for (Classes cls : classes) {
            listClasses.add(cls.getName());
        }

        txtAddClass.setOnAction(event -> {
            int selectedIndex = txtAddClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                selectedClass = classes.get(selectedIndex);
                System.out.println("Chọn class name: " + selectedClass.toString());
                this.showListSubjectOfClass();
            }
        });
        txtAddClass.getItems().addAll(listClasses);
    }

    public void showListSubjectOfClass(){
        ArrayList<Semester> listSemesterByCourse = SemesterDAO.getInstance().selectByCourseId(selectedClass.getCourse().getId());
        ArrayList<Subject> subjects = new ArrayList<>();
        for(Semester s : listSemesterByCourse){
            subjects.addAll(Semester_SubjectDAO.getInstance().getListSubjectBySemesterID(s.getId()));
        }

        ArrayList<String> listSubject = new ArrayList<>();

        for (Subject sub : subjects) {
            listSubject.add(sub.getCode());
        }

        txtAddSubject.setOnAction(event -> {
            int selectedIndex = txtAddSubject.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < subjects.size()) {
                selectedSubject = subjects.get(selectedIndex);
                System.out.println("Chọn subject name: " + selectedSubject.getName());
            }
        });
        txtAddSubject.getItems().clear();
        txtAddSubject.getItems().addAll(listSubject);
    }

    public void showTxtStatus(){
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Lý Thuyết");
        map.put(1, "Thực Hành");
        map.put(2, "N/A");

        txtAddFormat.getItems().addAll(map.values());

        txtAddFormat.setOnAction(event -> {
            String selectedItem = txtAddFormat.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    if (entry.getValue().equals(selectedItem)) {
                        selectedFormat = entry.getKey();
                        break;
                    }
                }
                System.out.println("Chọn status: " + selectedFormat);
                this.showListSubjectOfClass();
            }
        });
    }

    public void setStage(Stage examStage) {
        this.examStage = examStage;
    }
    @FXML
    void quit(ActionEvent event) {
        examStage.close();
    }
}
