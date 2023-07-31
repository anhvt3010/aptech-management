package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.*;
import com.anhvt.aptechmanagement.Utils.SelectedClassStorage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {
    @FXML
    public Button btnQuit;
    @FXML
    public ChoiceBox<String> txtTypeSubject;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Score> tblScoreStudent;
    @FXML
    private TableColumn<Score, String> tcCode;
    @FXML
    private TableColumn<Score, Integer> tcID;
    @FXML
    private TableColumn<Score, String> tcName;
    @FXML
    private TableColumn<Score, Integer> tcPercent;
    @FXML
    private TableColumn<Score, Integer> tcScore;

    @FXML
    private TableColumn<Score, String> tcStatus;

    @FXML
    private ChoiceBox<String> txtClass;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private ChoiceBox<String> txtSemester;

    @FXML
    private TextField txtStatus;

    @FXML
    private ChoiceBox<String> txtSubject;

    private Stage scoreStage;
    private Classes selectedClass;
    private Semester selectedSemester;
    private Subject selectedSubject;
    private int selectedTypeSubject;

    private ObservableList<Score> scores;

    private final ArrayList<Student> listStudentByClass = Student_LearnDAO.getInstance()
            .selectAllStudentsByClass(SelectedClassStorage.getSelectedClass());
    private final ArrayList<Semester> listSemesterByCourse = SemesterDAO.getInstance()
            .selectByCourseId(SelectedClassStorage.getSelectedClass().getCourse().getId());
    private ArrayList<Subject> listSubjectBySemester = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showListSemester();
        this.showListSubject();
        this.showTypeSubject();

        scores = FXCollections.observableArrayList();

//        this.showTableScore();

    }

    public void showTableScore(){
        if(selectedTypeSubject == 1){
            for(Student student : listStudentByClass){
                scores.add(ScoreDAO.getInstance().selectByIdStudentAndIdSubjectLT(student.getId(), selectedSubject.getId()));
            }
        } else {
            for(Student student : listStudentByClass){
                scores.add(ScoreDAO.getInstance().selectByIdStudentAndIdSubjectTH(student.getId(), selectedSubject.getId()));
            }
        }
        tblScoreStudent.setItems(scores);
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCode.setCellValueFactory(cellData -> {
            String code = Student_LearnDAO.getInstance().selectById(cellData.getValue().getId()).getStudent_code();
            return new SimpleStringProperty(code);
        });

    }

    public void showListSemester(){
        txtSemester.setOnAction(event -> {
            int selectedIndex = txtSemester.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < listSemesterByCourse.size()) {
                selectedSemester = listSemesterByCourse.get(selectedIndex);
// goi ham show subject
                listSubjectBySemester = Semester_SubjectDAO.getInstance()
                        .getListSubjectBySemesterID(selectedSemester.getId());
                this.showListSubject();
                System.out.println("Chọn semester name: " + selectedSemester.getName());
            }
        });

        ArrayList<String> listSemesterNames = new ArrayList<>();
        for (Semester semester : listSemesterByCourse) {
            listSemesterNames.add(semester.getName());
        }
        txtSemester.getItems().clear();
        txtSemester.getItems().addAll(listSemesterNames);
    }

    public void showListSubject(){
        txtSubject.setOnAction(event -> {
            int selectedIndex = txtSubject.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < listSubjectBySemester.size()) {
                selectedSubject = listSubjectBySemester.get(selectedIndex);
                System.out.println("Chọn subject name: " + selectedSubject.getName());
            }
        });

        ArrayList<String> listSubjectNames = new ArrayList<>();
        for (Subject subject : listSubjectBySemester) {
            listSubjectNames.add(subject.getName());
        }
        txtSubject.getItems().clear();
        txtSubject.getItems().addAll(listSubjectNames);
    }
    @FXML
    public void chooseTypeSubject(MouseEvent mouseEvent) {

    }

    public void showTypeSubject(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Lý Thuyết");
        map.put(2, "Thực Hành");
        map.put(3, "Đồ Án");

        ObservableList<String> types = FXCollections.observableArrayList("Lý Thuyết", "Thực Hành", "Đồ Án");
        txtTypeSubject.setItems(types);

        txtTypeSubject.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getValue().equals(newValue)) {
                    selectedTypeSubject = entry.getKey();
                    System.out.println("type subject id: " + selectedTypeSubject);
                    break;
                }
            }
        });
    }


    @FXML
    void save(ActionEvent event) {

    }
    @FXML
    void update(ActionEvent event) {

    }
    public void quit(ActionEvent actionEvent) {
        this.scoreStage.close();
    }
    public void setScoreStage(Stage scoreStage) {
        this.scoreStage = scoreStage;
    }

    public void setSelectedClass(Classes classes) {
        this.selectedClass = classes;
    }

    public Classes getSelectedClass() {
        return selectedClass;
    }
}
