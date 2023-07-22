package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.DAO.ScheduleDAO;
import com.anhvt.aptechmanagement.DAO.SemesterDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Schedule;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ScheduleController extends SideBarController implements Initializable {

    @FXML
    public Button btnSubmit;
    @FXML
    public TextField txtLink;

    @FXML
    private ChoiceBox<String> btnListClass;

    @FXML
    private ChoiceBox<String> btnListCourse;

    @FXML
    private ChoiceBox<String> btnListSem;


    Classes classSelected = null;
    Course courseSelected = null;
    Semester semesterSelected = null;

    ArrayList<Semester> semesters = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        hiển thị danh sách lớp

        ArrayList<Classes> classes = ClassDAO.getIntance().findAll();
        ArrayList<String> listClass = new ArrayList<>();

        for (Classes cls : classes) {
            listClass.add(cls.getName());
        }

        btnListClass.setOnAction(event -> {
            int selectedIndex = btnListClass.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < classes.size()) {
                classSelected = classes.get(selectedIndex);
                System.out.println("Chọn class name: " + classSelected.getName());
            }
        });

        btnListClass.getItems().addAll(listClass);

//        hiển thị danh sách khóa học

        ArrayList<Course> courses = CourseDAO.getIntance().findAllCourse();
        ArrayList<String> listCourse = new ArrayList<>();

        for (Course course : courses) {
            listCourse.add(course.getName());
        }

        btnListCourse.setOnAction(event -> {
            int selectedIndex = btnListCourse.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < courses.size()) {
                courseSelected = courses.get(selectedIndex);
                semesters = SemesterDAO.getInstance().selectByCourseId(courses.get(selectedIndex).getId());
                System.out.println("Chọn class name: " + courses.get(selectedIndex).getName());
                this.callbtnListSem();
            }
        });
        System.out.println(semesters.size());
        btnListCourse.getItems().addAll(listCourse);
    }

    public void callbtnListSem(){
        btnListSem.setOnAction(event -> {
            int selectedIndex = btnListSem.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < semesters.size()) {
                semesterSelected = semesters.get(selectedIndex); // Cập nhật semesterSelected với đối tượng tương ứng
                System.out.println("Chọn semester name: " + semesterSelected.getName());
            }
        });

        // Tạo danh sách tên các semester để thêm vào nút btnListSemester (nếu bạn muốn)
        ArrayList<String> listSemesterNames = new ArrayList<>();
        for (Semester semester : semesters) {
            listSemesterNames.add(semester.getName());
        }
        btnListSem.getItems().clear();
        btnListSem.getItems().addAll(listSemesterNames);
    }


    @FXML
    public void submitUpload(ActionEvent actionEvent) {
        Schedule schedule = new Schedule();
        schedule.setClasses(classSelected);
        schedule.setSemester(semesterSelected);
        schedule.setLink(txtLink.getText());

        ScheduleDAO.getInstance().insert(schedule);

        AlertUtil.showInforEAlert("Thông báo", "Thêm mới thành công","");

        txtLink.clear();
    }
}
