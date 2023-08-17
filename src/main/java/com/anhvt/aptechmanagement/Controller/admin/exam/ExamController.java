package com.anhvt.aptechmanagement.Controller.admin.exam;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.ExamDAO;
import com.anhvt.aptechmanagement.DAO.SemesterDAO;
import com.anhvt.aptechmanagement.DAO.Semester_SubjectDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Exam;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Property.ExamProperty;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Validation.Validation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ExamController extends SideBarController implements Initializable {
    private Stage examStage;
    @FXML
    public Button addExam;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> txtDetailClass;

    @FXML
    private DatePicker txtDetailDate;

    @FXML
    private ChoiceBox<String> txtDetailFormat;

    @FXML
    private TextArea txtDetailName;

    @FXML
    private ChoiceBox<String> txtDetailSubject;

    @FXML
    private TableView<ExamProperty> tblExam;

    @FXML
    private TableColumn<ExamProperty, String> tcClass;

    @FXML
    private TableColumn<ExamProperty, LocalDate> tcDate;

    @FXML
    private TableColumn<ExamProperty, String> tcFormat;

    @FXML
    private TableColumn<ExamProperty, Integer> tcID;

    @FXML
    private TableColumn<ExamProperty, String> tcName;

    @FXML
    private TableColumn<ExamProperty, String> tcStatus;

    @FXML
    private TableColumn<ExamProperty, String> tcSubject;


    ObservableList<ExamProperty> exams;

    ArrayList<Classes> classes = ClassDAO.getIntance().findAll();
    ArrayList<Subject> subjects = new ArrayList<>();

    ExamProperty examProperty;
    private Classes selectedClass = null;
    private Subject selectedSubject = null;
    int selectedFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.turn_off_field();

        this.checkAndUpdateStatus();

        exams = FXCollections.observableList(ExamDAO.getInstance().findAllProperty());

        tblExam.setItems(exams);

        tcID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcClass.setCellValueFactory(cellData -> {
            String cl = cellData.getValue().getClasses().getName();
            return new SimpleStringProperty(cl);
        });
        tcSubject.setCellValueFactory(cellData -> {
            String sub = cellData.getValue().getSubject().getCode();
            return new SimpleStringProperty(sub);
        });
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcDate.setCellValueFactory(cellData -> cellData.getValue().exam_dayProperty());
        tcFormat.setCellValueFactory(cellData -> cellData.getValue().formatProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tblExam.setRowFactory(tv -> {
            TableRow<ExamProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    examProperty = row.getItem();
                    handle_selectedExam(examProperty);
                    this.turn_off_field();
                }
            });

            return row;
        });

        this.showListClasses();
    }

    private void handle_selectedExam(ExamProperty examProperty) {
        txtDetailClass.setValue(examProperty.classesProperty().getValue().getName());
        txtDetailSubject.setValue(examProperty.subjectProperty().getValue().getCode());
        txtDetailDate.setValue(examProperty.exam_dayProperty().getValue());
        txtDetailName.setText(examProperty.nameProperty().getValue());
        this.showTxtFormat();
        txtDetailFormat.setValue(examProperty.formatProperty().getValue());
    }
    @FXML
    public void getFormAddExam(ActionEvent actionEvent) {
        try {
            if (examStage != null && examStage.isShowing()) {
                // Nếu cửa sổ môn học đã tồn tại, đưa cửa sổ hiện tại lên phía trước
                examStage.toFront();
            } else {
                // Nếu cửa sổ môn học chưa tồn tại, tạo mới cửa sổ và hiển thị
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/test/addExamUI.fxml"));
                Parent root = loader.load();

                examStage = new Stage();
                examStage.setTitle("Thêm Lịch Thi");
                examStage.setScene(new Scene(root));

                examStage.setOnCloseRequest(t -> {
                    examStage = null;
                });

                AddExamController controller = loader.getController();
                controller.setStage(examStage); // Đặt đối tượng Stage vào controller

                examStage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void update(ActionEvent actionEvent) {
        if(examProperty != null) {
            this.turn_on_field();


        // lấy lại đối tượng khi ko cần chỉnh sửa
            String selectedSubjectCode = txtDetailSubject.getValue();
            selectedSubject = null;
            for (Subject subject : subjects) {
                if (subject.getCode().equals(selectedSubjectCode)) {
                    selectedSubject = subject;
                    break;
                }
            }
        // lấy lại đối tượng khi ko cần chỉnh sửa
            String selectedClassName = txtDetailClass.getValue();
            selectedClass = null;
            for (Classes cls : classes) {
                if (cls.getName().equals(selectedClassName)) {
                    selectedClass = cls;
                    break;
                }
            }
        }
    }
    @FXML
    public void save(ActionEvent actionEvent) {
        Exam exam = new Exam();

        String name = txtDetailName.getText().trim();
        LocalDate date = txtDetailDate.getValue();

        // Kiểm tra dữ liệu nhập vào
        if (Validation.isNullOrEmpty(name) || date == null) {
            AlertUtil.showErrorAlert("Lỗi", "Dữ liệu không hợp lệ", "Vui lòng điền đầy đủ thông tin môn học.");
            return;
        }

        exam.setId(examProperty.getId());
        exam.setClasses(selectedClass);
        exam.setSubject(selectedSubject);
        exam.setExam_day(txtDetailDate.getValue());
        exam.setName(txtDetailName.getText());
        exam.setFormat((byte) selectedFormat);
        exam.setStatus((byte) (examProperty.getStatus().equals("Chưa Thi")?0:1));

        ExamDAO.getInstance().update(exam);

        exams.setAll(ExamDAO.getInstance().findAllProperty());
        AlertUtil.showInforEAlert("Thông Báo", "Sửa lịch thi thành công", "");
        btnSave.setDisable(true);
    }

    public void showListClasses(){

        ArrayList<String> listClasses = new ArrayList<>();

        for (Classes cls : classes) {
            listClasses.add(cls.getName());
        }

        txtDetailClass.setOnAction(event -> {
            int selectedIndex = txtDetailClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                selectedClass = classes.get(selectedIndex);
                System.out.println("Chọn class name: " + selectedClass.toString());
                this.showListSubjectOfClass();
            }
        });
        txtDetailClass.getItems().addAll(listClasses);
    }

    public void showListSubjectOfClass(){
        ArrayList<Semester> listSemesterByCourse = SemesterDAO.getInstance().selectByCourseId(selectedClass.getCourse().getId());
        for(Semester s : listSemesterByCourse){
            subjects.addAll(Semester_SubjectDAO.getInstance().getListSubjectBySemesterID(s.getId()));
        }
        ArrayList<String> listSubject = new ArrayList<>();

        for (Subject sub : subjects) {
            listSubject.add(sub.getCode());
        }

        txtDetailSubject.setOnAction(event -> {
            int selectedIndex = txtDetailSubject.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < subjects.size()) {
                selectedSubject = subjects.get(selectedIndex);
                System.out.println("Chọn subject name: " + selectedSubject.getName());
            }
        });
        txtDetailSubject.getItems().clear();
        txtDetailSubject.getItems().addAll(listSubject);
    }
    public void showTxtFormat(){
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Lý Thuyết");
        map.put(1, "Thực Hành");

        txtDetailFormat.getItems().clear();
        txtDetailFormat.getItems().addAll(map.values());

        txtDetailFormat.setOnAction(event -> {
            String selectedItem = txtDetailFormat.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    if (entry.getValue().equals(selectedItem)) {
                        selectedFormat = entry.getKey();
                        break;
                    }
                }
                System.out.println("Chọn status: " + selectedFormat);
            }
        });
    }

    public void checkAndUpdateStatus() {
        ArrayList<Exam> exams = ExamDAO.getInstance().findAll();
        LocalDate currentDate = LocalDate.now();

        for (Exam exam : exams) {
            System.out.println(exam.getId() + "  " + exam.getExam_day() +"  "+ currentDate);
            // nếu ngày thi < hiện tại mà ngày thi là chưa thi --> set Đã Thi
            if (exam.getExam_day().isBefore(currentDate) && exam.getStatus() == 0) {
                System.out.println(exam.getStatus());
                exam.setStatus((byte) 1);
                System.out.println(exam.getStatus());

                ExamDAO.getInstance().updateStatusInDatabase(exam);
            }
        }
    }

    public void turn_off_field(){
        txtDetailClass.setDisable(true);
        txtDetailSubject.setDisable(true);
        txtDetailName.setDisable(true);
        txtDetailFormat.setDisable(true);
        txtDetailDate.setDisable(true);
        btnSave.setDisable(true);
    }
    public void turn_on_field(){
        txtDetailClass.setDisable(false);
        txtDetailSubject.setDisable(false);
        txtDetailName.setDisable(false);
        txtDetailFormat.setDisable(false);
        txtDetailDate.setDisable(false);
        btnSave.setDisable(false);
    }
}
