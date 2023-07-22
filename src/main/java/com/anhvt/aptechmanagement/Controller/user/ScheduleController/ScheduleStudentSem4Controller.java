package com.anhvt.aptechmanagement.Controller.user.ScheduleController;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.ScheduleDAO;
import com.anhvt.aptechmanagement.DAO.Student_LearnDAO;
import com.anhvt.aptechmanagement.Model.Schedule;
import com.anhvt.aptechmanagement.Model.Student_Learn;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleStudentSem4Controller extends SideBarController implements Initializable {

    @FXML
    private Button btnScheduleSem1;

    @FXML
    private Button btnScheduleSem2;

    @FXML
    private Button btnScheduleSem3;


    @FXML
    private Button openButton;

    @FXML
    private WebView showSchedule;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHelloStudent.setText("Xin chào " + Session.getAttribute().getLastName() );

//        lấy id student
        int id_student = Session.getAttribute().getId();
//        lấy đối tượng student_learn có id class
        Student_Learn id_class = Student_LearnDAO.getInstance().selectByStudentID(id_student);
//        lấy đối tượng schedule từ id stuednt và id class
        Schedule schedule = ScheduleDAO.getInstance().selectByClassIDAndSemesterID(id_class.getClasses().getId(), 4);
        String scheduleLink = schedule.getLink();

        System.out.println("kì 4 " + scheduleLink);

        if(scheduleLink != null){
            showSchedule.getEngine().load(scheduleLink);
        } else {
            showSchedule.setVisible(false);
        }

        openButton.setOnAction(e -> openURL(scheduleLink));
    }
    @FXML
    void showScheduleSem1(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScheduleSem1();
    }

    @FXML
    void showScheduleSem2(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScheduleSem2();
    }

    @FXML
    void showScheduleSem3(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScheduleSem3();
    }

    private void openURL(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.err.println("Desktop browsing is not supported on this platform.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
