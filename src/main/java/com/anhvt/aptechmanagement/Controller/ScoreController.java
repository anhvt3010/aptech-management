package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.*;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.SelectedClassStorage;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ScoreController implements Initializable {
    private static final Logger logger = LogManager.getLogger(ScoreController.class);
    @FXML
    public Button btnQuit;
    @FXML
    public ChoiceBox<String> txtTypeSubject;
    @FXML
    public TextField txtScore;
    @FXML
    public ChoiceBox<String> txtMaxScore;
    @FXML
    public Button btnAddFromExcel;
    @FXML
    public Text txtNameCourse;
    @FXML
    public TextField txtSearch;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Student> tblScoreStudent;
    @FXML
    private TableColumn<Student, String> tcCode;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Score, Integer> tcPercent;

    @FXML
    private TableColumn<Score, String> tcScore;

    @FXML
    private TableColumn<Student, String> tcStatus;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private ChoiceBox<String> txtSemester;

    @FXML
    private ChoiceBox<String> txtSubject;

    private Stage scoreStage;
    private Classes selectedClass;
    private Semester selectedSemester;
    private Student selectedStudent;
    private int scoreMax;
    Subject selectedSubject = null;
    private int selectedTypeSubject;

    ObservableList<Student> students;

    private final ArrayList<Student> listStudentByClass = Student_LearnDAO.getInstance()
            .selectAllStudentsByClass(SelectedClassStorage.getSelectedClass());
    private final ArrayList<Semester> listSemesterByCourse = SemesterDAO.getInstance()
            .selectByCourseId(SelectedClassStorage.getSelectedClass().getCourse().getId());
    private ArrayList<Subject> listSubjectBySemester = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNameCourse.setText(SelectedClassStorage.getSelectedClass().getCourse().getName());
        tblScoreStudent.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        this.textOFF();
        this.showListSemester();
        this.showScoreMax();

        students = FXCollections.observableList(listStudentByClass);
        tblScoreStudent.setItems(students);
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCode.setCellValueFactory(cellData -> {
            String code = Student_LearnDAO.getInstance()
                    .selectByStudentID(cellData.getValue().getId()).getStudent_code();
            return new SimpleStringProperty(code);
        });
        tcName.setCellValueFactory(cellData -> {
            String name;
            name = cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName();
            return new SimpleStringProperty(name);
        });

        tblScoreStudent.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedStudent = row.getItem();
                    System.out.println(selectedStudent.getEmail());
                    handle_selectedClass(selectedStudent);
                    handleTcScore();
                    btnUpdate.setDisable(false);
                }
            });
            return row;
        });

        FilteredList<Student> filteredStudents = new FilteredList<>(students, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredStudents.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return student.getFirstName().toLowerCase().contains(lowerCaseFilter) ||
                        student.getLastName().toLowerCase().contains(lowerCaseFilter);
            });
        });

        tblScoreStudent.setItems(filteredStudents);

        txtScore.textProperty().addListener((observable, oldValue, newValue) -> {
            if (InputTextValidator.isValidScore(newValue)) {
                enableBtnSave();
            } else {
                btnSave.setDisable(true);
            }
        });
    }

    private void handle_selectedClass(Student selectedStudent) {
        txtScore.clear();
        txtCode.setText(Student_LearnDAO.getInstance().selectByStudentID(selectedStudent.getId()).getStudent_code());

        txtName.setText(selectedStudent.getFirstName() + " " + selectedStudent.getLastName());

        if(handleTcScore().getId() == 0){
            txtScore.setPromptText("Chưa có điểm");
        } else {
            txtScore.setText(String.valueOf(handleTcScore().getScore()));
        }

    }

    public Score handleTcScore(){
        Score selectedScore;
        if(selectedTypeSubject == 1){
            selectedScore = ScoreDAO.getInstance()
                    .selectByIdStudentAndIdSubjectLT(selectedStudent.getId(), selectedSubject.getId());
        } else {
            selectedScore = ScoreDAO.getInstance()
                    .selectByIdStudentAndIdSubjectTH(selectedStudent.getId(), selectedSubject.getId());
        }

        return selectedScore;
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
                this.showTypeSubject();
                System.out.println("Chọn subject name: " + selectedSubject.getName());

            }
        });

        ArrayList<String> listSubjectNames = new ArrayList<>();
        for (Subject subject : listSubjectBySemester) {
            listSubjectNames.add(subject.getCode());
        }
        txtSubject.getItems().clear();
        txtSubject.getItems().addAll(listSubjectNames);

    }

    public void showTypeSubject(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Lý Thuyết");
        map.put(2, "Thực Hành");
        map.put(3, "Đồ Án");

        ObservableList<String> types = FXCollections.observableArrayList("Lý Thuyết", "Thực Hành", "Đồ Án");
        txtTypeSubject.setItems(types);

        txtTypeSubject.setOnAction(event -> {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getValue().equals(txtTypeSubject.getValue())) {
                    selectedTypeSubject = entry.getKey();
                    tblScoreStudent.setDisable(false);
                    System.out.println("type subject id: " + selectedTypeSubject);
                    break;
                }
            }
        });
    }
    public void showScoreMax(){
        ObservableList<String> max = FXCollections.observableArrayList("20", "100");
        txtMaxScore.setItems(max);
        txtMaxScore.setOnAction(event -> {
            for (String s : max) {
                if (s.equals(txtMaxScore.getValue())) {
                    scoreMax = Integer.parseInt(s);
                    System.out.println("score max: " + scoreMax);
                    break;
                }
            }
        });
    }



    @FXML
    void save(ActionEvent event) {
        if(txtScore.getText().isEmpty()){
            ScoreDAO.getInstance().insert(this.getObjectScore());
            AlertUtil.showInforEAlert("Thông báo", "Thêm điểm thành công", "");
        } else {
            ScoreDAO.getInstance().update(this.getObjectScore());
            AlertUtil.showInforEAlert("Thông báo", "Cập nhât điểm thành công", "");

        }
    }

    public Score getObjectScore(){
        Score score = new Score();

        score.setStudent(selectedStudent);
        score.setSubject(selectedSubject);
        if (!txtScore.getText().isEmpty()) {
            try {
                int scoreValue = Integer.parseInt(txtScore.getText());
                score.setScore(scoreValue);
            } catch (NumberFormatException e) {
                // Xử lý nếu người dùng nhập không phải là số
                System.out.println("Không phải số nguyên");
            }
        }
        score.setScore_max(scoreMax);
        score.setType((byte) selectedTypeSubject);

        return score;
    }
    @FXML
    void update(ActionEvent event) {
        this.textON();
    }

    public void textOFF(){
        txtCode.setDisable(true);
        txtName.setDisable(true);
        txtScore.setDisable(true);
    }
    public void textON(){
        txtCode.setDisable(true);
        txtName.setDisable(true);
        txtScore.setDisable(false);
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
    private void enableBtnSave() {
        boolean isTxtMaxScoreEmpty = txtMaxScore.getValue() == null || txtMaxScore.getValue().isEmpty();
        boolean isTxtScoreEmpty = txtScore.getText().isEmpty();

        btnSave.setDisable(isTxtMaxScoreEmpty || isTxtScoreEmpty);
    }
    @FXML
    public void addFromExcel(ActionEvent actionEvent) {
    }
}
