package com.anhvt.aptechmanagement.Controller.admin.score;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.*;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.ExcelReader;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ScoreController implements Initializable {

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
    public Text txtNameClass;
    @FXML
    public Text txtNameFile;
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
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private ChoiceBox<String> txtSemester;

    @FXML
    private ChoiceBox<String> txtSubject;

    private Stage scoreStage;
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
    private ArrayList<ObjectFromExcelScore> scores = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNameCourse.setText(SelectedClassStorage.getSelectedClass().getCourse().getName());
        txtNameClass.setText(SelectedClassStorage.getSelectedClass().getName());
        tblScoreStudent.setDisable(true);
        btnAddFromExcel.setDisable(true);
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
// sự kiện chọn row để lấy đối tượng
        tblScoreStudent.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedStudent = row.getItem();
                    System.out.println(selectedStudent.getEmail());
//                    hiển thị thông tin chi tiết student
                    handle_selectedClass(selectedStudent);
                    handleTcScore();
                    this.textOFF();
                    btnUpdate.setDisable(false);
                }
            });
            return row;
        });
// tính năng search
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
//    nhập điểm để bật nút Save
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
                    btnAddFromExcel.setDisable(false);
                    System.out.println("type subject id: " + selectedTypeSubject);
                    break;
                }
            }
        });
    }
    public void showScoreMax(){
        ObservableList<String> max = FXCollections.observableArrayList("20", "100");
        txtMaxScore.setItems(max);
        txtMaxScore.setValue("20");
        scoreMax = 20;
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
        if(!ScoreDAO.getInstance().isScoreExists(this.getObjectScore())){
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
        score.setScore(Integer.parseInt(txtScore.getText()));
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
    private void enableBtnSave() {
        boolean isTxtMaxScoreEmpty = txtMaxScore.getValue() == null || txtMaxScore.getValue().isEmpty();
        boolean isTxtScoreEmpty = txtScore.getText().isEmpty();

        btnSave.setDisable(isTxtMaxScoreEmpty || isTxtScoreEmpty);
    }
    @FXML
    public void addFromExcel(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File selectedFile = fileChooser.showOpenDialog(scoreStage);

        if (selectedFile == null) {
            AlertUtil.showInforEAlert("Thông Báo", "Không có file nào được chọn", "");
        } else {
            txtNameFile.setText(selectedFile.getName());

            int indexOfUnderscore  = selectedFile.getName().indexOf("_");
            if (indexOfUnderscore != -1) {
                String className = selectedFile.getName().substring(0, indexOfUnderscore);
                if(className.equalsIgnoreCase(SelectedClassStorage.getSelectedClass().getName())){
                    this.checkInputFileNull(selectedFile);
                    this.addListScore();
                    AlertUtil.showInforEAlert("Thông báo", "Thêm Danh sách điểm thành công", "");
                } else {
                    AlertUtil.showErrorAlert("Lỗi", "Lớp được chọn không đúng !", "");
                }
            } else {
                AlertUtil.showErrorAlert("Lỗi", "Lớp được chọn không đúng !", "");
            }
        }
    }

    public void checkInputFileNull(File selectedFile){
        if (selectedFile != null) {
            if (selectedFile.getName().toLowerCase().endsWith(".xlsx")) {
                try {
//                    lấy ra danh sách đối tượng có mã HV và điểm
                    scores = ExcelReader.readExcel(selectedFile.getAbsolutePath());
                    for(ObjectFromExcelScore obj : scores){
                        System.out.println(obj.toString());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("File không hợp lệ");
                alert.setHeaderText(null);
                alert.setContentText("Bạn phải chọn một tệp Excel (.xlsx). Vui lòng chọn lại.");
                alert.showAndWait();
            }
        }
    }
    public void addListScore(){
        //        lấy ra danh sách student_learn để lấy danh sách student_id theo mã HV
        ArrayList<Score> listAddScore = new ArrayList<>();

        for(ObjectFromExcelScore obj : scores){
            // lấy ra đối tượng từ objecj có mã hs
            Student_Learn studentLearn = Student_LearnDAO.getInstance().selectByStudentCode(obj.getCodeStudent());
            System.out.println(studentLearn.toString());
            Score score = new Score();
            score.setScore(obj.getScore());
            score.setScore_max(scoreMax);
            score.setStudent(StudentDAO.getInstance().selectById(studentLearn.getStudent().getId()));
            score.setSubject(selectedSubject);
            score.setType((byte) selectedTypeSubject);

            ScoreDAO.getInstance().insert(score);

        }
    }
}
