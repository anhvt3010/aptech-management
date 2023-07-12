package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.Entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseController extends SideBarController implements Initializable {
    @FXML
    private TableView<Course> tbCourse;

    @FXML
    private TableColumn<Course, Integer> tcId;

    @FXML
    private TableColumn<Course, String> tcName;

    @FXML
    private TableColumn<Course, Integer> tcSem;

    @FXML
    private TableColumn<Course, String> tcStatus;

    ObservableList<Course> courses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courses = FXCollections.observableArrayList();
        List<Course> courseList = CourseDAO.getIntance().findAll();
        courses.addAll(courseList);
        // add du lieu vao bang
        tbCourse.setItems(courses);
        // add du lieu vao tung cot
    }
}
