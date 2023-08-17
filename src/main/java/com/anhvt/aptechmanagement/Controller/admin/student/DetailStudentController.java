package com.anhvt.aptechmanagement.Controller.admin.student;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.GetAddressFromAPI;
import com.anhvt.aptechmanagement.Utils.MergeAddress;
import com.anhvt.aptechmanagement.Utils.SelectedIDStorage;
import com.anhvt.aptechmanagement.Validation.EmailValidator;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import com.anhvt.aptechmanagement.Validation.PhoneNumberValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DetailStudentController implements Initializable {
    private Stage detailStudentStage;
    @FXML
    public Button btnQuit;
    @FXML
    public AnchorPane formAddInformation;
    @FXML
    public TextField txtStudentCode;
    @FXML
    public TextField txtAddStudentCode;

    @FXML
    private ChoiceBox<String> btnListClass;

    @FXML
    private ChoiceBox<String> btnListCourse;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private RadioButton btnStatus;

    @FXML
    private RadioButton genderFemale;

    @FXML
    private RadioButton genderMale;
    @FXML
    private ComboBox<String> xa;

    @FXML
    private ComboBox<String> huyen;

    @FXML
    private ComboBox<String> tinh;

    @FXML
    private DatePicker txtBirth;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtlang;

    @FXML
    private TextField txtClass;

    @FXML
    private TextField txtCourse;

// su dung trong splitAddress va hien thi ra button
    Student student = StudentDAO.getInstance().selectById(SelectedIDStorage.getSelectedIDStorage());
    Student_Learn studentLearn = Student_LearnDAO.getInstance().selectByStudentID(student.getId());

// su dung trong formAddInfor
    Classes classSelected = null;
    Course courseSelected = null;

// lấy ra danh sách các xa, huyen, tinh tu API
    Map<Integer, String> provinces = GetAddressFromAPI.getInstance().getProvinceNamesFromAPI();
    Map<Integer, String>  districts = new HashMap<>();
    Map<Integer, String>  communes = new HashMap<>();

// xa huyen tinh duoc chon
    int code_province_selected;
    int code_district_selected;
    int code_commune_selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.turn_text_field_OFF();
        this.showInformationDetail();
        this.getFormAnchorPane();

        txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtLastName.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtPhone.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtBirth.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtAddStudentCode.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
    }

    @FXML
    void saveStudent(ActionEvent event) {
// ----- update Student -------
        StudentDAO.getInstance().update(this.getObjectStudent());

// ----- update Student_Learn -------
        Student_LearnDAO.getInstance().update(this.getObjectStudent_Learn());
        AlertUtil.showInforEAlert("Thông báo", "Chỉnh sửa học viên thành công","");
        try {
            this.detailStudentStage.close();
            Navigator.getInstance().gotoStudent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void showListClasses(){
        ArrayList<Classes> classes = ClassDAO.getIntance().findAll();
        ArrayList<String> listClass = new ArrayList<>();

        for (Classes cls : classes) {
            listClass.add(cls.getName());
        }

        btnListClass.setOnAction(event -> {
            int selectedIndex = btnListClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                classSelected = classes.get(selectedIndex);
                System.out.println("Chọn class name: " + classSelected.getName());
            }
        });
        btnListClass.getItems().addAll(listClass);

        btnListClass.setValue(studentLearn.getClasses().getName());
    }

    void showListCourses(){
        ArrayList<Course> courses = CourseDAO.getIntance().findAllCourse();
        ArrayList<String> listCourse = new ArrayList<>();

        for (Course course : courses) {
            listCourse.add(course.getName());
        }

        btnListCourse.setOnAction(event -> {
            int selectedIndex = btnListCourse.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < courses.size()) {
                courseSelected = courses.get(selectedIndex);
                System.out.println("Chọn course name: " + courseSelected.getName());
            }
        });
        btnListCourse.getItems().addAll(listCourse);

        btnListCourse.setValue(studentLearn.getCourse().getName());
    }

    private Student getObjectStudent(){
        Student studentUpdate = new Student();

        studentUpdate.setId(student.getId());
        studentUpdate.setFirstName(txtFirstName.getText());
        studentUpdate.setLastName(txtLastName.getText());
        studentUpdate.setGender(genderMale.isSelected());
        studentUpdate.setBirth(txtBirth.getValue());
        studentUpdate.setPhone(txtPhone.getText());
        studentUpdate.setEmail(txtEmail.getText());
        studentUpdate.setAddress(MergeAddress
                .getInstance()
                .mergeAddress(txtlang.getText(), xa.getValue(), huyen.getValue(), tinh.getValue()));
        studentUpdate.setStatus((byte) (btnStatus.isSelected()?1:0));

        return studentUpdate;
    }

    private Student_Learn getObjectStudent_Learn(){
        Student_Learn studentLearn = Student_LearnDAO.getInstance().selectByStudentID(student.getId());

        studentLearn.setClasses(classSelected);
        studentLearn.setCourse(courseSelected);
        studentLearn.setStudent_code(txtAddStudentCode.getText());

        return studentLearn;
    }
    void turn_text_field_OFF(){
        txtFirstName.setEditable(false);
        txtLastName.setEditable(false);
        genderFemale.setDisable(true);
        genderMale.setDisable(true);
        txtBirth.setDisable(true);
        txtPhone.setEditable(false);
        txtEmail.setEditable(false);
        tinh.setDisable(true);
        huyen.setDisable(true);
        xa.setDisable(true);
        txtlang.setEditable(false);
        btnStatus.setDisable(true);
        txtCourse.setEditable(false);
        txtClass.setEditable(false);
        txtStudentCode.setEditable(false);
    }

    void turn_text_field_ON(){
        txtFirstName.setEditable(true);
        txtLastName.setEditable(true);
        genderFemale.setDisable(false);
        genderMale.setDisable(false);
        txtBirth.setDisable(false);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        tinh.setDisable(false);
        huyen.setDisable(false);
        xa.setDisable(false);
        txtlang.setEditable(true);
        btnStatus.setDisable(false);
        txtCourse.setDisable(true);
        txtClass.setDisable(true);
        txtStudentCode.setDisable(true);
    }

    void setPlaceholderStudent(){
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
        if (txtAddStudentCode.getText().isEmpty()) {
            txtAddStudentCode.setPromptText("Nhập Mã HV...");
        }

    }

    public static String[] splitString(String input, String delimiter) {
        return input.split(delimiter);
    }

    void showListProvinces(){
        tinh.setValue(this.splitAddress(student.getAddress()).get(3));

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

    void showListDistricts(){
        huyen.setValue(this.splitAddress(student.getAddress()).get(2));

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

    void showListWards(){
        xa.setValue(this.splitAddress(student.getAddress()).get(1));

        xa.setOnAction(event -> {
            String selectedCommune = xa.getValue();
            for (Map.Entry<Integer, String> entry : communes.entrySet()) {
                if (entry.getValue().equals(selectedCommune)) {
                    code_commune_selected = entry.getKey();
                    System.out.println("code_commune_selected: " + code_commune_selected);
                    break;
                }
            }
        });
    }

    public ArrayList<String> splitAddress(String address){
        ArrayList<String> addresses = new ArrayList<>();
        String[] result = splitString(student.getAddress(), ", ");

        if (result.length >= 4) {
            StringBuilder combinedAddress = new StringBuilder();
            for (int i = 0; i < result.length - 3; i++) {
                if (i > 0) {
                    combinedAddress.append(", ");
                }
                combinedAddress.append(result[i]);
            }
            addresses.add(combinedAddress.toString());
            // Thêm giá trị xa
            addresses.add(result[result.length - 3]);
            // Thêm giá trị huyen
            addresses.add(result[result.length - 2]);
            // Thêm giá trị tinh
            addresses.add(result[result.length - 1]);
        } else {
            System.out.println("Không đúng định dạng");
        }
        return addresses;
    }

    public void setDetailStudentStage(Stage detailStudentStage) {
        this.detailStudentStage = detailStudentStage;
    }

    public void quit(ActionEvent actionEvent) {
        this.detailStudentStage.close();
    }

    public void getFormAnchorPane(){
        formAddInformation.setVisible(false);
        btnUpdate.setOnAction(event -> {
            formAddInformation.setVisible(true);
            this.turn_text_field_ON();
            this.showListClasses();
            this.showListCourses();
            txtAddStudentCode.setText(txtStudentCode.getText());
        });
    }

    public void showInformationDetail(){
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        if(student.getGender()){
            genderMale.setSelected(true);
        } else {
            genderFemale.setSelected(true);
        }
        txtPhone.setText(student.getPhone());
        txtBirth.setValue(student.getBirth());
        txtEmail.setText(student.getEmail());
        txtStudentCode.setText(studentLearn.getStudent_code());
//        Xử lí Địa Chi
        txtlang.setText(this.splitAddress(student.getAddress()).get(0));

        this.showListProvinces();
        this.showListDistricts();
        this.showListWards();
//  ----------------------------------------------------
        btnStatus.setSelected(student.getStatus() == 1);
        Student_Learn studentLearn = Student_LearnDAO
                .getInstance()
                .selectByStudentID(student.getId());
        if(studentLearn != null){
            txtClass.setText(studentLearn.getClasses().getName());
            txtCourse.setText(studentLearn.getCourse().getName());
        } else {
            txtClass.setText("Chưa có lớp học");
            txtCourse.setText("Chưa tham gia khóa học");
        }
    }

    private void updateSaveButtonStatus() {
        boolean allFieldsFilled = EmailValidator.isValid(txtEmail.getText()) &&
                PhoneNumberValidator.isValidPhoneNumber(txtPhone.getText()) &&
                InputTextValidator.isValidDatePicker(txtBirth) &&
                InputTextValidator.isValidName(txtFirstName.getText()) &&
                InputTextValidator.isValidName(txtLastName.getText()) &&
                InputTextValidator.isValidStudentCode(txtAddStudentCode.getText());
        btnSave.setDisable(!allFieldsFilled);
        this.setPlaceholderStudent();
    }
}
