package com.anhvt.aptechmanagement.Controller.student;

import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.GetAddressFromAPI;
import com.anhvt.aptechmanagement.Utils.Passwordefault;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

    @FXML
    private DatePicker txtBirth;

    @FXML
    private Button btnReset;

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

    @FXML
    void resetForm(ActionEvent event) {
        txtFirstName.clear();
        txtLastName.clear();
        txtBirth.setValue(null);
        txtPhone.clear();
        txtEmail.clear();
        tinh.setValue(null);
        huyen.setValue(null);
        xa.setValue(null);
        txtlang.clear();
    }

    @FXML
    void saveStudent(ActionEvent event) throws IOException {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        Boolean gender = null;
        if(genderMale.isSelected()){
            gender = true;
        }
        if(genderFemale.isSelected()){
            gender = false;
        }
        String phone = txtPhone.getText();

        LocalDate birth = null;
        if (txtBirth.getValue() != null) {
            Date date = Date.valueOf(txtBirth.getValue());
            birth = date.toLocalDate();
        }
        String email = txtEmail.getText();
        String address = txtlang.getText() + ", " + xa.getValue() + ", " + huyen.getValue() + ", " + tinh.getValue();

        LocalDate created = LocalDate.now();
        LocalDate yearOfAdmission = created.plusYears(2).plusMonths(6);

        Byte status = null;
        if(btnStatus.isSelected()){
            status = 1;
        } else {
            status = 0;
        }

        if (firstName == null || lastName == null || gender == null || phone == null || txtBirth.getValue() == null ||
            email == null || txtlang.getText() == null || xa.getValue() == null || huyen.getValue() == null ||
            tinh.getValue() == null || status == null) {

            AlertUtil.showErrorAlert("Lưu Thất Bại",
                    "Lưu Thất Bại !",
                    "Vui lòng điền đầy đủ thông tin");
        } else {
            Student student = new Student(firstName, lastName, gender,birth,phone,email,
                    Passwordefault.getInstance().getPassworDefault(),address, created, status);
            StudentDAO.getIntance().insert(student);
        }

        Navigator.getInstance().gotoStudent();

    }

    Map<Integer, String>  provinces = GetAddressFromAPI.getInstance().getProvinceNamesFromAPI();
    Map<Integer, String>  districts = new HashMap<>();
    Map<Integer, String>  communes = new HashMap<>();


    int code_province_selected;
    int code_district_selected;
    int code_commune_selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

}
