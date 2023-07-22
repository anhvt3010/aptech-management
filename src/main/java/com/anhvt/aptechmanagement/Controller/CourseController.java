package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Property.CourseProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseController extends SideBarController implements Initializable {
    int selected_id;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNum;

    @FXML
    private RadioButton btnStatus;

    @FXML
    private TableView<CourseProperty> tbCourse;

    @FXML
    private TableColumn<CourseProperty, Integer> tcId;

    @FXML
    private TableColumn<CourseProperty, String> tcName;

    @FXML
    private TableColumn<CourseProperty, Integer> tcSem;

    @FXML
    private TableColumn<CourseProperty, String> tcStatus;

    ObservableList<CourseProperty> courses;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courses = FXCollections.observableArrayList();
        ArrayList<CourseProperty> courseList = CourseDAO.getIntance().findAll();

        courses.setAll(courseList);
        // add du lieu vao bang
        tbCourse.setItems(courses);
        // add du lieu vao tung cot
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tcSem.setCellValueFactory(cellData -> cellData.getValue().semesterProperty().asObject());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tbCourse.setRowFactory(tv -> {
            TableRow<CourseProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    CourseProperty selectedCourse = row.getItem();
                    handleselectedCourseSelection(selectedCourse);
                }
            });

            return row;
        });

    }

    public void handleselectedCourseSelection(CourseProperty course){
        this.setDisableButton();
        System.out.println("Selected Student: " + course.getName());
        selected_id = course.getId();
        txtName.setText(course.getName());
        txtNum.setText(String.valueOf(course.getSemester()));
        if(course.getStatus().equals("LOCK")){
            btnStatus.setSelected(false);
        } else {
            btnStatus.setSelected(true);
        }

    }
    @FXML
    public void saveCourse(ActionEvent actionEvent) {
        Course course = new Course();
        course.setId(selected_id);
        course.setName(txtName.getText());
        course.setSemester(Integer.parseInt(txtNum.getText()));
        course.setStatus((byte) (btnStatus.isSelected() == true?1:0));

        CourseProperty courseProperty = new CourseProperty(course);

        CourseDAO.getIntance().update(courseProperty);

        courses.setAll(CourseDAO.getIntance().findAll());

    }
    @FXML
    public void updateCourse(ActionEvent actionEvent) {
        this.setEnableButton();
    }


    public void setEnableButton(){
        txtName.setDisable(false);
        txtNum.setDisable(false);
        btnStatus.setDisable(false);
    }
    public void setDisableButton(){
        txtName.setDisable(true);
        txtNum.setDisable(true);
        btnStatus.setDisable(true);
    }


}
