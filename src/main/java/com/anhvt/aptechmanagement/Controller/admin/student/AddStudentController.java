package com.anhvt.aptechmanagement.Controller.admin.student;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.GetAddressFromAPI;
import com.anhvt.aptechmanagement.Utils.PasswordEncoder;
import com.anhvt.aptechmanagement.Utils.Passwordefault;
import com.anhvt.aptechmanagement.Validation.EmailValidator;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import com.anhvt.aptechmanagement.Validation.PhoneNumberValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


public class AddStudentController implements Initializable {

    private Stage addStudentStage;

    @FXML
    public ChoiceBox<String> txtAddCourse;
    @FXML
    public ChoiceBox<String> txtAddClass;
    @FXML
    public TextField txtAddCode;
    @FXML
    private DatePicker txtBirth;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton genderFemale;

    @FXML
    private RadioButton genderMale;

    @FXML
    private ComboBox<String> huyen;

    @FXML
    private TextField txtPhone;

    @FXML
    private RadioButton btnStatus;

    @FXML
    private ComboBox<String> tinh;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtlang;

    @FXML
    private ComboBox<String> xa;

    Map<Integer, String>  provinces = GetAddressFromAPI.getInstance().getProvinceNamesFromAPI();
    Map<Integer, String>  districts = new HashMap<>();
    Map<Integer, String>  communes = new HashMap<>();

    int code_province_selected;
    int code_district_selected;
    int code_commune_selected;

    int selectedCourseId;
    int selectedClassId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSave.setDisable(true);
        this.setPlaceholderStudent();
        this.showListProvince();
        this.showListDistrict();
        this.showListCommune();
        this.showListCourse();
        this.showListClass();

        txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtLastName.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        genderMale.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        genderFemale.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtPhone.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtBirth.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtlang.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        xa.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        huyen.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        tinh.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtAddCourse.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtAddClass.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtAddCode.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
    }

    @FXML
    void saveStudent(ActionEvent event) throws IOException {
        StudentDAO.getInstance().insertStudent_StudentLearn(Objects.requireNonNull(this.createObjectStudent()), this.createObjectStudent_Learn());
        AlertUtil.showInforEAlert("Thông Báo", "Thêm học viên thành công", "");
        Navigator.getInstance().gotoStudent();
    }

    private void showListProvince(){
        List<String> listProvinceName = new ArrayList<>(provinces.values());
        tinh.getItems().addAll(listProvinceName);
        tinh.setOnAction(event -> {
            String selectedProvince = tinh.getValue();
            for (Map.Entry<Integer, String> entry : provinces.entrySet()) {
                if (entry.getValue().equals(selectedProvince)) {
                    code_province_selected = entry.getKey();
                    System.out.println("code_province_selected: "+ code_province_selected);
                    break;
                }
            }
            districts = GetAddressFromAPI.getInstance().getDistrictsFromAPI(code_province_selected);
            List<String> listDistrictNames = new ArrayList<>(districts.values());

            huyen.getItems().clear();
            huyen.getItems().addAll(listDistrictNames);

        });
    }

    private void showListDistrict() {
        huyen.setOnAction(event -> {
            String selectedDistrict = huyen.getValue();
            for (Map.Entry<Integer, String> entry : districts.entrySet()) {
                if (entry.getValue().equals(selectedDistrict)) {
                    code_district_selected = entry.getKey();
                    System.out.println("code_district_selected: "+ code_district_selected);
                    break;
                }
            }
            communes = GetAddressFromAPI.getInstance().getCommunesFromAPI(code_district_selected);
            List<String> listCommuneNames = new ArrayList<>(communes.values());

            xa.getItems().clear();
            xa.getItems().addAll(listCommuneNames);
        });
    }

    private void showListCommune(){
        xa.setOnAction(event -> {
            String selectedCommune = xa.getValue();
            for (Map.Entry<Integer, String> entry : communes.entrySet()) {
                if (entry.getValue().equals(selectedCommune)) {
                    code_commune_selected = entry.getKey();
                    System.out.println("code_commune_selected: "+ code_commune_selected);
                    break;
                }
            }
        });
    }

    @FXML
    void quit(ActionEvent event) {
        this.addStudentStage.close();
    }

    private Student createObjectStudent(){
        Student student = new Student();
        student.setFirstName(txtFirstName.getText());
        student.setLastName(txtLastName.getText());
        student.setGender(genderMale.isSelected());
        student.setPhone(txtPhone.getText());
        LocalDate birth = null;
        if (txtBirth.getValue() != null) {
            Date date = Date.valueOf(txtBirth.getValue());
            birth = date.toLocalDate();
        }
        student.setBirth(birth);
        student.setPassword(PasswordEncoder.encodePassword(Passwordefault.getInstance().getPassworDefault()));
        student.setEmail(txtEmail.getText());
        String address = txtlang.getText() + ", " + xa.getValue() + ", " + huyen.getValue() + ", " + tinh.getValue();
        student.setAddress(address);
        student.setCreated(LocalDate.now());
        if(btnStatus.isSelected()){
            student.setStatus((byte) 1);
        } else {
            student.setStatus((byte) 0);
        }
        return student;
    }

    private Student_Learn createObjectStudent_Learn() {
        Student_Learn student_learn = new Student_Learn();
        student_learn.setCourse(CourseDAO.getIntance().selectByIdCourse(selectedCourseId));
        student_learn.setClasses(ClassDAO.getIntance().selectById(selectedClassId));
        student_learn.setStudent_code(txtAddCode.getText());
        return student_learn;
    }

    private void showListCourse(){
        ArrayList<Course> courses = CourseDAO.getIntance().findAllCourse();
        Map<Integer, String> courseMap = new HashMap<>();
        for (Course course : courses) {
            courseMap.put(course.getId(), course.getName());
        }
        ObservableList<String> courseNames = FXCollections.observableArrayList(courseMap.values());
        txtAddCourse.setItems(courseNames);

        txtAddCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Map.Entry<Integer, String> entry : courseMap.entrySet()) {
                if (entry.getValue().equals(newValue)) {
                    selectedCourseId = entry.getKey();
                    this.showListClass();
                    System.out.println("Course id: " + selectedCourseId);
                    break;
                }
            }
        });
    }

    private void showListClass(){
        ArrayList<Classes> classes = ClassDAO.getIntance().findClassesByCourseID(selectedCourseId);
//        if(classes.isEmpty()){
//            txtAddClass.setDisable(true);
//        }
        Map<Integer, String> classMap = new HashMap<>();
        for (Classes cls : classes) {
            classMap.put(cls.getId(), cls.getName());
        }
        ObservableList<String> classNames = FXCollections.observableArrayList(classMap.values());
        txtAddClass.setItems(classNames);

        txtAddClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Map.Entry<Integer, String> entry : classMap.entrySet()) {
                if (entry.getValue().equals(newValue)) {
                    selectedClassId = entry.getKey();
                    System.out.println("Class id: " + selectedClassId);
                    break;
                }
            }
        });
    }

    private void updateSaveButtonStatus() {
        boolean allFieldsFilled = xa.getValue() != null &&
                huyen.getValue() != null &&
                tinh.getValue() != null &&
                txtAddClass.getValue() != null &&
                txtAddCourse.getValue() != null &&
                EmailValidator.isValid(txtEmail.getText()) &&
                PhoneNumberValidator.isValidPhoneNumber(txtPhone.getText()) &&
                InputTextValidator.isValidDatePicker(txtBirth) &&
                InputTextValidator.isValidName(txtFirstName.getText()) &&
                InputTextValidator.isValidName(txtLastName.getText()) &&
                InputTextValidator.isValidStudentCode(txtAddCode.getText());
        btnSave.setDisable(!allFieldsFilled);
    }

    public void setPlaceholderStudent() {
        if (txtFirstName.getText().isEmpty()) {
            txtFirstName.setPromptText("Nhập họ...");
        }
        if (txtLastName.getText().isEmpty()) {
            txtLastName.setPromptText("Nhập tên...");
        }
        if (!genderMale.isSelected()) {
            genderMale.setSelected(true);
            genderFemale.setSelected(false);
        }
        if (txtBirth.getValue() == null) {
            txtBirth.setPromptText("Nhập ngày sinh...");
        }
        if (txtPhone.getText().isEmpty()) {
            txtPhone.setPromptText("Nhập số ĐT...");
        }
        if (txtEmail.getText().isEmpty()) {
            txtEmail.setPromptText("Nhập email...");
        }
        if (txtAddCode.getText().isEmpty()) {
            txtAddCode.setPromptText("Nhập Mã HV...");
        }
    }

    public void setStage(Stage stage){
        this.addStudentStage = stage;
    }

}
