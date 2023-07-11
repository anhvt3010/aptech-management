package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SideBarController {
    @FXML
    private Button btnClass;

    @FXML
    private Button btnCourse;

    @FXML
    private Button btnDocument;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnStudent6;

    @FXML
    private Button btnTest;

    @FXML
    void gotoClass(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoClass();
    }

    @FXML
    void gotoCourse(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoCourse();
    }

    @FXML
    void gotoDocument(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoDocument();
    }

    @FXML
    void gotoSchedule(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoSchedule();
    }

    @FXML
    void gotoStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoStudent();
    }

    @FXML
    void gotoTest(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoTest();
    }
}
