package com.anhvt.aptechmanagement.Controller.admin;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.ScheduleDAO;
import com.anhvt.aptechmanagement.DAO.SemesterDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Schedule;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleController extends SidebarAdminController implements Initializable {

    @FXML
    public Button btnSaveAdd;
    @FXML
    public TextField txtAddLink;
    @FXML
    public AnchorPane formAddSchedule;
    @FXML
    public TextField txtDetailLink;
    @FXML
    public Button btnSaveUpdate;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnAdd;
    @FXML
    public TextField txtDetailClass;
    @FXML
    public TextField txtDetailSemester;

    @FXML
    private ChoiceBox<String> choiceAddClass;

    @FXML
    private ChoiceBox<String> choiceAddSemester;

    @FXML
    private TableView<Schedule> tblSchedule;
    @FXML
    private TableColumn<Schedule, String> tcClassName;
    @FXML
    private TableColumn<Schedule, Integer> tcID;
    @FXML
    private TableColumn<Schedule, Hyperlink> tcLink;
    @FXML
    private TableColumn<Schedule, String> tcSemester;

    ObservableList<Schedule> schedules;

    private Schedule selectedSchedule = null;

    private Classes classSelected = null;
    Semester semesterSelected = null;

    ArrayList<Semester> semesters = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        hiển thị danh sách lớp
        this.showListClasses();

        schedules = FXCollections.observableList(ScheduleDAO.getInstance().findAll());
        tblSchedule.setItems(schedules);
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcClassName.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getClasses().getName();
            return new SimpleStringProperty(name);
        });
        tcSemester.setCellValueFactory(cellData -> {
            String sem = cellData.getValue().getSemester().getName();
            String cou = cellData.getValue().getSemester().getCourse().getName();

            return new SimpleStringProperty(sem + "-" + cou);
        });
        tcLink.setCellValueFactory(cellData -> {
            String link = cellData.getValue().getLink();
            Hyperlink hyperlink = new Hyperlink(link);
            hyperlink.setOnAction(event -> showForm(link));
            return new SimpleObjectProperty<>(hyperlink);
        });

        tblSchedule.setRowFactory(tv -> {
            TableRow<Schedule> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                this.disable_detailSchedule();
                btnSaveUpdate.setVisible(false);
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedSchedule = row.getItem();
                    this.handleSelectedSchedule(selectedSchedule);
                }
            });
            return row;
        });

        this.disable_detailSchedule();
        formAddSchedule.setVisible(false);
        btnSaveAdd.setDisable(true);
        btnSaveUpdate.setVisible(false);
        choiceAddClass.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonAdd());
        choiceAddSemester.valueProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonAdd());
        txtAddLink.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonAdd());

        txtDetailLink.textProperty().addListener((observable, oldValue, newValue) -> updateButtonUpdate());

    }


    void showListClasses(){
        ArrayList<Classes> classes = ClassDAO.getIntance().findAll();
        ArrayList<String> listClass = new ArrayList<>();

        for (Classes cls : classes) {
            listClass.add(cls.getName());
        }

        choiceAddClass.setOnAction(event -> {
            int selectedIndex = choiceAddClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                classSelected = classes.get(selectedIndex);
                this.showListSemester();
                System.out.println("Chọn class name: " + classSelected.toString());
            }
        });

        choiceAddClass.getItems().addAll(listClass);
    }
    public void showListSemester(){
        semesters = SemesterDAO.getInstance().selectByCourseId(classSelected.getCourse().getId());
        choiceAddSemester.setOnAction(event -> {
            int selectedIndex = choiceAddSemester.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < semesters.size()) {
                semesterSelected = semesters.get(selectedIndex); // Cập nhật semesterSelected với đối tượng tương ứng
                System.out.println("Chọn semester name: " + semesterSelected.getName());
            }
        });

        // Tạo danh sách tên các semester để thêm vào nút btnListSemester
        ArrayList<String> listSemesterNames = new ArrayList<>();
        for (Semester semester : semesters) {
            if(!ScheduleDAO.getInstance().checkLinkExisted(semester, classSelected)){
                listSemesterNames.add(semester.getName() + "-" + classSelected.getCourse().getName());
            }
        }
        choiceAddSemester.getItems().clear();
        choiceAddSemester.getItems().addAll(listSemesterNames);
    }


    public void showForm(String link){
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            try {
                java.net.URI uri = new java.net.URI(link);
                desktop.browse(uri);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private void updateSaveButtonAdd() {
        boolean allFieldsFilled = choiceAddClass.getValue() != null &&
                choiceAddSemester.getValue() != null &&
                !txtAddLink.getText().isEmpty() &&
                InputTextValidator.isValidURL(txtAddLink.getText());
        btnSaveAdd.setDisable(!allFieldsFilled);
    }

    private void updateButtonUpdate() {
        boolean allFieldsFilled = !txtDetailLink.getText().isEmpty() &&
                InputTextValidator.isValidURL(txtDetailLink.getText());
        btnSaveUpdate.setDisable(!allFieldsFilled);
    }
    @FXML
    public void saveAdd(ActionEvent actionEvent) {
        Schedule schedule = new Schedule();
        schedule.setClasses(classSelected);
        schedule.setSemester(semesterSelected);
        schedule.setLink(txtAddLink.getText());

        ScheduleDAO.getInstance().insert(schedule);
        schedules.setAll(ScheduleDAO.getInstance().findAll());
        AlertUtil.showInforEAlert("Thông báo", "Thêm mới thành công","");

        txtAddLink.clear();
    }
    @FXML
    public void saveUpdate(ActionEvent actionEvent) {
        Schedule schedule = selectedSchedule;
        schedule.setLink(txtDetailLink.getText());

        ScheduleDAO.getInstance().update(schedule);
        schedules.setAll(ScheduleDAO.getInstance().findAll());
        AlertUtil.showInforEAlert("Thông Báo", "Cập nhật thành công !", "");

    }

    @FXML
    public void update(ActionEvent actionEvent) {
        this.enable_detailSchedule();
        btnSaveUpdate.setVisible(true);
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        formAddSchedule.setVisible(true);
    }

    private void handleSelectedSchedule(Schedule schedule){
        txtDetailClass.setText(schedule.getClasses().getName());
        txtDetailSemester.setText(schedule.getSemester().getName() +
                "-" + schedule.getSemester().getCourse().getName());
        txtDetailLink.setText(schedule.getLink());
    }

    private void disable_detailSchedule(){
        txtDetailClass.setDisable(true);
        txtDetailSemester.setDisable(true);
        txtDetailLink.setDisable(true);
        btnSaveUpdate.setDisable(true);
    }
    private void enable_detailSchedule(){
        txtDetailLink.setDisable(false);
    }
}
