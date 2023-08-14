package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.DAO.SroDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddClassController implements Initializable {
    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSave;

    @FXML
    private ChoiceBox<String> choiceSRO;

    @FXML
    private ChoiceBox<String> choiceCourse;

    @FXML
    private ChoiceBox<String> choiceTypeNotification;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNum;

    private Stage stageClass;
    Course selectedCourse = null;
    Staff selectedSRO = null;
    int selectedType = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showListCourses();
        this.showListSRO();
        this.showTypeClass();
        this.callEventUpdateBtnSave();

        btnSave.setDisable(true);
    }

    private void showListCourses() {
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
        Map<Integer, String> mapFormat = new HashMap<>();
        mapFormat.put(0, "SÁNG");
        mapFormat.put(1, "CHIỀU");
        mapFormat.put(2, "TỐI");
        choiceTypeNotification.getItems().addAll(mapFormat.values());
        choiceTypeNotification.setValue("SÁNG");

        choiceTypeNotification.setOnAction(event -> {
            int selectedIndex = choiceTypeNotification.getSelectionModel().getSelectedIndex();
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
                !txtName.getText().isEmpty() &&
                !txtNum.getText().isEmpty() &&
                !txtDescription.getText().isEmpty() &&
                InputTextValidator.isValidInteger(txtNum);
        btnSave.setDisable(!allFieldsFilled);
    }


    public void setStageClass(Stage stageClass) {
        this.stageClass = stageClass;
    }

    @FXML
    public void save() {
        Classes cls = new Classes();
        cls.setStaff(selectedSRO);
        cls.setCourse(selectedCourse);
        cls.setName(txtName.getText());
        cls.setDescription(txtDescription.getText());
        cls.setLimit(Integer.parseInt(txtNum.getText()));
        cls.setCreated(LocalDate.now());
        cls.setType((byte) selectedType);

        ClassDAO.getIntance().insert(cls);
        AlertUtil.showInforEAlert("Thông Báo", "Thêm Lớp thành công", "");

        stageClass.close();

    }
    @FXML
    public void quit() {
        stageClass.close();
    }
}
