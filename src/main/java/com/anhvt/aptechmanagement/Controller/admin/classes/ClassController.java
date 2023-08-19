package com.anhvt.aptechmanagement.Controller.admin.classes;

import com.anhvt.aptechmanagement.Controller.admin.SidebarAdminController;
import com.anhvt.aptechmanagement.Controller.admin.score.ScoreController;
import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.SelectedClassStorage;
import com.anhvt.aptechmanagement.Utils.WindowManager;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ClassController extends SidebarAdminController implements Initializable {
    @FXML
    public Button btnInputScore;
    @FXML
    public ChoiceBox<String> choiceCourse;
    @FXML
    public ChoiceBox<String> choiceSRO;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtNum;
    @FXML
    public TextArea txtDescription;
    @FXML
    public ChoiceBox<String> choiceType;

    @FXML
    public Button btnUpdate;

    @FXML
    public Button btnSave;
    @FXML
    public Button btnAdd;

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
    Map<Integer, String> mapFormat = new HashMap<>();

    Classes selectedClass = null;
    Course selectedCourse = null;
    Staff selectedSRO = null;
    int selectedType;

    ObservableList<Classes> classes;
    ObservableList<Student> students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.disable_detailClass();
        this.showTableClass();
        this.showListCourses();
        this.showListSRO();
        this.showTypeClass();
        this.callEventUpdateBtnSave();

        btnInputScore.setDisable(true);
    }

    private void showTableClass(){
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
                this.disable_detailClass();
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedClass = row.getItem();
                    System.out.println(selectedClass.getName());
                    SelectedClassStorage.setSelectedClass(selectedClass);
                    this.handle_selectedClass(selectedClass);
                    this.handle_detailSelectedClass(selectedClass);
                    btnInputScore.setDisable(false);
                }
            });
            return row;
        });
    }
    private void handle_detailSelectedClass(Classes selectedClass) {
        choiceCourse.setValue(selectedClass.getCourse().getName());

        String codeSRO = SroDAO.getInstance().selectById(selectedClass.getStaff().getId()).getCode();
        String first_name = SroDAO.getInstance().selectById(selectedClass.getStaff().getId()).getLast_name();
        String last_name = SroDAO.getInstance().selectById(selectedClass.getStaff().getId()).getFirst_name();
        choiceSRO.setValue(codeSRO + "-" + last_name + " " + first_name);
        txtName.setText(selectedClass.getName());
        txtNum.setText(selectedClass.getLimit() + "");
        txtDescription.setText(selectedClass.getDescription());

        for (Map.Entry<Integer, String> entry : mapFormat.entrySet()) {
            if(entry.getKey() == (int)selectedClass.getType()){
                choiceType.setValue(entry.getValue());
            }
        }

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
    public void gotoInputScore() {
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
                WindowManager.addStage(scoreStage);

                scoreStage.showAndWait();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void update() {
        if (selectedClass != null) {
            this.enable_detailClass();
        } else {
            System.out.println("selectedClass is not initialized.");
        }
    }
    @FXML
    public void save() {
        ClassDAO.getIntance().update(this.getObjectClass());
        AlertUtil.showInforEAlert("Thông Báo", "Sửa lớp học thành công", "");

        classes.setAll(ClassDAO.getIntance().findAll());
    }

    private void showListCourses(){
        ArrayList<Course> courses = CourseDAO.getIntance().findAllCourse();
        ArrayList<String> listCourse = new ArrayList<>();

        for (Course course : courses) {
            listCourse.add(course.getName());
        }

        choiceCourse.setOnAction(event -> {
            int selectedIndex = choiceCourse.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < courses.size()) {
                selectedCourse = courses.get(selectedIndex);
                System.out.println("Chọn course name: " + courses.get(selectedIndex).getName());
            }
        });
        choiceCourse.getItems().addAll(listCourse);
    }

    private void showListSRO(){
        ArrayList<Staff> staffs = SroDAO.getInstance().findAll();
        ArrayList<String> listSRO = new ArrayList<>();

        for (Staff st : staffs) {
            String codeSRO = st.getCode();
            String first_name = st.getLast_name();
            String last_name = st.getFirst_name();
            listSRO.add(codeSRO + "-" + last_name + " " + first_name);
        }

        choiceSRO.setOnAction(event -> {
            int selectedIndex = choiceSRO.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < staffs.size()) {
                selectedSRO = staffs.get(selectedIndex);
                System.out.println("Chọn course name: " + staffs.get(selectedIndex).getCode());
            }
        });
        choiceSRO.getItems().addAll(listSRO);
    }

    private void showTypeClass(){
        mapFormat.put(0, "SÁNG");
        mapFormat.put(1, "CHIỀU");
        mapFormat.put(2, "TỐI");
        choiceType.getItems().addAll(mapFormat.values());

        choiceType.setOnAction(event -> {
            int selectedIndex = choiceType.getSelectionModel().getSelectedIndex();
            if(selectedIndex >= 0 && selectedIndex < mapFormat.size()){
                System.out.println(mapFormat.get(selectedIndex));
                for (Map.Entry<Integer, String> entry : mapFormat.entrySet()) {
                    if(mapFormat.get(selectedIndex).equals(entry.getValue())){
                        selectedType = entry.getKey();
                        System.out.println("key type: " + selectedType);
                    }
                }
            }
        });
    }

    private Classes getObjectClass(){
        Classes cls = selectedClass;
        cls.setStaff(selectedSRO);
        cls.setCourse(selectedCourse);
        cls.setName(txtName.getText());
        cls.setDescription(txtDescription.getText());
        cls.setLimit(Integer.parseInt(txtNum.getText()));
        cls.setType((byte) selectedType);
        return cls;
    }

    private void callEventUpdateBtnSave(){
        choiceCourse.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButton());
        choiceSRO.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButton());
        choiceSRO.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButton());
        txtName.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButton());
        txtNum.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButton());
        txtDescription.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButton());
    }

    private void updateSaveButton(){
        boolean allFieldsFilled = choiceCourse.getValue() != null &&
                choiceSRO.getValue() != null &&
                choiceType.getValue() != null &&
                !txtName.getText().isEmpty() &&
                !txtNum.getText().isEmpty() &&
                !txtDescription.getText().isEmpty() &&
                InputTextValidator.isValidInteger(txtNum);
        btnSave.setDisable(!allFieldsFilled);
    }

    private void disable_detailClass(){
        choiceCourse.setDisable(true);
        choiceSRO.setDisable(true);
        choiceType.setDisable(true);
        txtName.setDisable(true);
        txtDescription.setDisable(true);
        txtNum.setDisable(true);

        btnSave.setDisable(true);
    }
    private void enable_detailClass(){
        choiceCourse.setDisable(false);
        choiceSRO.setDisable(false);
        choiceType.setDisable(false);
        txtName.setDisable(false);
        txtDescription.setDisable(false);
        txtNum.setDisable(false);
    }

    @FXML
    public void add() {
        try {
            if (scoreStage != null && scoreStage.isShowing()) {
                scoreStage.toFront();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/class/addClassUI.fxml"));
                Parent root = loader.load();

                scoreStage = new Stage();
                scoreStage.setTitle("Thêm môn học");
                scoreStage.setScene(new Scene(root));
                scoreStage.setOnCloseRequest(t -> {
                    scoreStage = null;
                });

                AddClassController controller = loader.getController();
                controller.setStageClass(scoreStage);
                WindowManager.addStage(scoreStage);

                scoreStage.showAndWait();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
