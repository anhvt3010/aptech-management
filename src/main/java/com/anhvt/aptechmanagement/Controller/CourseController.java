package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.Model.Course;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
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
        ArrayList<Course> courseList = CourseDAO.getIntance().findAll();
        courses.addAll(courseList);
        // add du lieu vao bang
        tbCourse.setItems(courses);
        // add du lieu vao tung cot
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcSem.setCellValueFactory(new PropertyValueFactory<>("semester"));
        tcStatus.setCellValueFactory(cellData -> {
            Byte statusValue = cellData.getValue().getStatus();
            String statusText;
            if (statusValue == 1) {
                statusText = "ACTIVE";
            } else if (statusValue == 2) {
                statusText = "UNACTIVE";
            } else {
                statusText = ""; // Xử lý giá trị không xác định (nếu có)
            }
            return new SimpleStringProperty(statusText);
        });
    }
}
