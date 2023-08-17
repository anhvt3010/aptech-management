package com.anhvt.aptechmanagement.Controller.admin.notification;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.*;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Validation.EmailValidator;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import com.anhvt.aptechmanagement.Validation.PhoneNumberValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class AddNotificationController implements Initializable {
    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox checkboxStatus;

    @FXML
    private ChoiceBox<String> choiceClass;

    @FXML
    private ChoiceBox<String> choiceCourse;

    @FXML
    private ChoiceBox<String> choiceStudent;

    @FXML
    private ChoiceBox<String> choiceTypeNotification;

    @FXML
    private TextArea txtContent;

    private Stage addNotificationStage;

    private Course selectedCourse;
    private Classes selectedClass;
    private Student selectedStudent;

    private String notificationTitle;
    private String notificationContent;
    private Map<Integer, String> notificationTypes = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSave.setDisable(true);
        checkboxStatus.setSelected(true);
        this.showListCourses();
        choiceCourse.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        choiceClass.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        choiceStudent.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        choiceTypeNotification.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtContent.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());

    }

    private void updateSaveButtonStatus() {
        boolean allFieldsFilled = choiceCourse.getValue() != null &&
                choiceClass.getValue() != null &&
                choiceStudent.getValue() != null &&
                choiceTypeNotification.getValue() != null &&
                !txtContent.getText().isEmpty();
        btnSave.setDisable(!allFieldsFilled);
    }

    private Notification getObject(){
        Notification noti = new Notification();
        noti.setTitle(notificationTitle);
        noti.setContent(txtContent.getText());
        noti.setStudent(selectedStudent);
        noti.setStatus((byte) (checkboxStatus.isSelected()?1:0));
        return noti;
    }

    @FXML
    public void save(javafx.event.ActionEvent actionEvent) throws IOException {
        NotificationDAO.getInstance().insert(this.getObject());
        AlertUtil.showInforEAlert("Thông Báo", "Thêm Thông Báo Thành Công", "");
        Navigator.getInstance().gotoNotificationAdmin();
        addNotificationStage.close();
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
                System.out.println("Chọn course: " + selectedCourse.toString());
                this.showListClassByCourser();
            }
        });
        choiceCourse.getItems().addAll(listCourse);
    }

    private void showListClassByCourser(){
        choiceClass.getItems().clear();
        ArrayList<Classes> classes = ClassDAO.getIntance().findClassesByCourseID(selectedCourse.getId());
        ArrayList<String> listClass = new ArrayList<>();

        for (Classes cl : classes) {
            listClass.add(cl.getName());
        }

        choiceClass.setOnAction(event -> {
            int selectedIndex = choiceClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                selectedClass = classes.get(selectedIndex);
                System.out.println("Chọn class: " + selectedClass.toString());
                this.showListStudentByClass();
            }
        });
        choiceClass.getItems().addAll(listClass);
    }

    private void showListStudentByClass(){
        selectedStudent = null;
        choiceStudent.getItems().clear();
        ArrayList<Student> students = Student_LearnDAO.getInstance().selectAllStudentsByClass(selectedClass);
        ArrayList<String> listStudent = new ArrayList<>();

        for (Student student : students) {
            String code = Student_LearnDAO.getInstance().selectByStudentID(student.getId()).getStudent_code();
            String name = student.getLastName() + " " + student.getFirstName();
            String disName = code + "-" + name;
            listStudent.add(disName);
        }

        choiceStudent.setOnAction(event -> {
            int selectedIndex = choiceStudent.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < students.size()) {
                selectedStudent = students.get(selectedIndex);
                this.showListNotification();
                System.out.println("Chọn student: " + selectedStudent.toString());
            }
        });
        choiceStudent.getItems().addAll(listStudent);
    }

    private void showListNotification(){
        choiceTypeNotification.getItems().clear();
        txtContent.setText("");

        notificationTypes.put(1, "Thi Lại");
        notificationTypes.put(2, "Tư Cách Thi");

        choiceTypeNotification.getItems().addAll(notificationTypes.values());

        choiceTypeNotification.setOnAction(event -> {
            String selectedNotification = choiceTypeNotification.getValue();
            notificationTitle = selectedNotification;
            if(notificationTitle == null){
                txtContent.setText("");
            } else {
                if (notificationTitle.equals(notificationTypes.get(1))) {
                    notificationContent = "Học Viên " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName()
                            + " cần thi lại môn ";
                    txtContent.setText(notificationContent);
                } else if (notificationTitle.equals(notificationTypes.get(2))) {
                    notificationContent = "Học Viên " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName()
                            + " không đủ yêu cầu thi môn ";
                    txtContent.setText(notificationContent);
                }
            }
            txtContent.requestFocus();
            //đặt dấu nháy vào cuối nội dung được hiển thị trong text area
            txtContent.positionCaret(txtContent.getText().length());

            System.out.println("Selected Notification: " + selectedNotification);
        });
    }



    @FXML
    public void quit(javafx.event.ActionEvent actionEvent) {
        addNotificationStage.close();
    }

    public void setAddNotificationStage(Stage addNotificationStage) {
        this.addNotificationStage = addNotificationStage;
    }


}
