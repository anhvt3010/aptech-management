package com.anhvt.aptechmanagement.Controller.admin.course;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.DAO.SemesterDAO;
import com.anhvt.aptechmanagement.DAO.Semester_SubjectDAO;
import com.anhvt.aptechmanagement.DAO.SubjectDAO;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Property.CourseProperty;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CourseController extends SideBarController implements Initializable {
    @FXML
    public AnchorPane formAdd;  @FXML
    public TextField txtAddName;    @FXML
    public RadioButton btnAddStatus;    @FXML
    public Button btnSaveAdd;

    @FXML
    public AnchorPane formUpdate;   @FXML
    public TextField txtUpdateName;    @FXML
    public RadioButton btnUpdateStatus;    @FXML
    public Button btnDetailSubject;
    @FXML
    public Button btnAddCourse;    @FXML
    public Button btnAddSemester;    @FXML
    public Button btnAddSubject;    @FXML
    public Button btnDelete;
    @FXML
    public Button btnSaveUpdate;
    @FXML
    public Button btnUpdate;

    @FXML
    private Button btnListSubject;


    @FXML
    private TableView<CourseProperty> tblCourse;
    @FXML
    private TableColumn<CourseProperty, Integer> tcId;
    @FXML
    private TableColumn<CourseProperty, String> tcName;
    @FXML
    private TableColumn<CourseProperty, Integer> tcSem;
    @FXML
    private TableColumn<CourseProperty, String> tcStatus;

    @FXML
    private TableView<Semester> tblSemester;
    @FXML
    private TableColumn<Semester, Integer> tcSemID;
    @FXML
    private TableColumn<Semester, String> tcSemName;

    @FXML
    private TableView<Subject> tblSubject;
    @FXML
    private TableColumn<Subject, String> tcSubCode;
    @FXML
    private TableColumn<Subject, Integer> tcSubID;
    @FXML
    private TableColumn<Subject, Integer> tcSubNum;
    @FXML
    private TableColumn<Subject, String> tcSubType;

    private Stage stageCourse;
    ObservableList<CourseProperty> courses;
    CourseProperty selectedCourse = null;
    Semester selectedSemester = null;
    Subject selectedSubject = null;

    int selected_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courses = FXCollections.observableArrayList();
        ArrayList<CourseProperty> courseList = CourseDAO.getIntance().findAll();

        courses.setAll(courseList);
        // add du lieu vao bang
        tblCourse.setItems(courses);
        // add du lieu vao tung cot
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcSem.setCellValueFactory(cellData -> {
            int num = SemesterDAO.getInstance().selectByCourseId(cellData.getValue().getId()).size();
            return new SimpleIntegerProperty(num).asObject();
        });
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tblCourse.setRowFactory(tv -> {
            TableRow<CourseProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedCourse = row.getItem();
                    handle_selectedCourse(selectedCourse);
                    this.getSemesterByRowCourse();
                }
            });

            return row;
        });

    }

    public void getSemesterByRowCourse(){
        ObservableList<Semester> semesters =FXCollections.observableList(SemesterDAO.getInstance().selectByCourseId(selectedCourse.getId()));

        tblSemester.setItems(semesters);
        tcSemID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSemName.setCellValueFactory(new PropertyValueFactory<>("name"));
//
        tblSemester.setRowFactory(tv -> {
            TableRow<Semester> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedSemester = row.getItem();
                    getSubjectByRowSemester(selectedSemester);
                }
            });

            return row;
        });
    }

    public void getSubjectByRowSemester(Semester semester){
//        lấy danh sách ID từ bảng Semester_subject
        List<Integer> listIDSubject = Semester_SubjectDAO.getInstance().selectBySemester(semester.getId());
//        lấy ra list đối tượng subject
        List<Subject> subjectList = new ArrayList<>();
        for(int id_sub : listIDSubject){
            subjectList.add(SubjectDAO.getIntance().selectById(id_sub));
        }

        ObservableList<Subject> subjects = FXCollections.observableList(subjectList);

        tblSubject.setItems(subjects);
        tcSubID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSubCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tcSubNum.setCellValueFactory(new PropertyValueFactory<>("number_of_sessions"));
        tcSubType.setCellValueFactory(cellData -> {
            Byte value = cellData.getValue().getType();
            if(value == 0){
                return new SimpleStringProperty("không BB");
            } else if (value == 1 ) {
                return new SimpleStringProperty("BB");
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
    }

    public void handle_selectedCourse(CourseProperty course){
        this.setDisableButton();
        System.out.println("Selected course: " + course.getName());
        selected_id = course.getId();
        txtUpdateName.setText(course.getName());
        btnUpdateStatus.setSelected(!course.getStatus().equals("Khóa"));

    }

    public void setEnableButton(){
        txtUpdateName.setDisable(false);
        btnUpdateStatus.setDisable(false);
    }
    public void setDisableButton(){
        txtUpdateName.setDisable(true);
        btnUpdateStatus.setDisable(true);
    }
    @FXML
    void gotoListSubject(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoListSubject();
    }
    @FXML
    public void saveAdd(ActionEvent actionEvent) {
        Course course = new Course();
        course.setName(txtAddName.getText());
        course.setSemester(0);
        course.setStatus((byte) (btnAddStatus.isSelected()?1:0));

        System.out.println(course);
        CourseDAO.getIntance().insertCourse(course);
        courses.setAll(CourseDAO.getIntance().findAll());
        AlertUtil.showInforEAlert("Thông Báo", "Thêm Khóa học thành công", "");
    }
    @FXML
    public void saveUpdate(ActionEvent actionEvent) {
        Course course = new Course();
        course.setId(selected_id);
        course.setName(txtUpdateName.getText());
        course.setStatus((byte) (btnUpdateStatus.isSelected() ?1:0));

        CourseProperty courseProperty = new CourseProperty(course);

        CourseDAO.getIntance().update(courseProperty);

        courses.setAll(CourseDAO.getIntance().findAll());
        AlertUtil.showInforEAlert("Thông Báo", "Sửa khóa học thành công", "");
    }
    @FXML
    public void update(ActionEvent actionEvent) {
        if(selectedCourse != null){
            this.setEnableButton();
            formUpdate.setVisible(true);
        }
    }
    @FXML
    public void delete(ActionEvent actionEvent) {
    }
    @FXML
    public void enable_formAdd(ActionEvent actionEvent) {
        formUpdate.setVisible(false);
        formAdd.setVisible(true);

        try {
            if (stageCourse != null && stageCourse.isShowing()) {
                stageCourse.toFront();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/course/addCourseUI.fxml"));
                Parent root = loader.load();

                stageCourse = new Stage();
                stageCourse.setTitle("Thêm Khóa học");
                stageCourse.setScene(new Scene(root));

                stageCourse.setOnCloseRequest(t -> {
                    stageCourse = null;
                });

                AddCourseController controller = loader.getController();
                controller.setStageAddCourse(stageCourse);

                stageCourse.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
