package com.anhvt.aptechmanagement.Controller.admin.subject;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.DAO.SemesterDAO;
import com.anhvt.aptechmanagement.DAO.Semester_SubjectDAO;
import com.anhvt.aptechmanagement.DAO.SubjectDAO;
import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Model.Semester;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Navigator;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubjectController extends SideBarController implements Initializable {
    @FXML
    public ChoiceBox<String> btnFindByCourse;
    @FXML
    public ChoiceBox<String> btnFindBySemester;
    @FXML
    public Button btnAddSubject;
    @FXML
    public Button btnAllSubject;
    @FXML
    public Button btnBackListCourse;
    @FXML
    public Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Subject> tblSubject;

    @FXML
    private TableColumn<Subject, String> tcSubCode;

    @FXML
    private TableColumn<Subject, String> tcSubDescription;

    @FXML
    private TableColumn<Subject, String> tcSubFormat;

    @FXML
    private TableColumn<Subject, Integer> tcSubID;

    @FXML
    private TableColumn<Subject, String> tcSubName;

    @FXML
    private TableColumn<Subject, Integer> tcSubNum;

    @FXML
    private TableColumn<Subject, String> tcSubType;

    private Stage subjectStage;

    ObservableList<Subject> subjects;

    Subject selectedSubject;

    Course courseSelected = null;
    Semester semesterSelected = null;

    ArrayList<Semester> semesters = new ArrayList<>();
    ArrayList<Subject> listSubjects = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        subjects = FXCollections.observableList(SubjectDAO.getIntance().findAll());
        tblSubject.setItems(subjects);

        this.showListCourses();

        tcSubID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSubName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcSubCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tcSubDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcSubNum.setCellValueFactory(new PropertyValueFactory<>("number_of_sessions"));
        tcSubFormat.setCellValueFactory(cellData -> {
            Byte value = cellData.getValue().getExam_format();

            if(value == 0){
                return new SimpleStringProperty("LT");
            } else if(value == 1){
                return new SimpleStringProperty("LT/TH");
            } else {
                return new SimpleStringProperty("Đồ Án");
            }
        });
        tcSubType.setCellValueFactory(cellData -> {
            Byte value = cellData.getValue().getExam_format();

            if(value == 0){
                return new SimpleStringProperty("Không BB");
            } else {
                return new SimpleStringProperty("BB");
            }
        });

        tblSubject.setRowFactory(tv -> {
            TableRow<Subject> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedSubject = row.getItem();
                }
            });
            return row;
        });


    }

    @FXML
    void deleteSubject(ActionEvent event) throws IOException {
        Subject sub = Semester_SubjectDAO.getInstance().checkRecordSubject(selectedSubject.getId());
        Alert comfirm = new Alert(Alert.AlertType.CONFIRMATION);
        if (sub.getId() > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cảnh Báo !");
            alert.setHeaderText("Môn học này có liên quan đến danh sách Học Kì");
            alert.setContentText("Bạn xóa môn học này đồng nghĩa với việc dữ liệu sẽ bị xóa !!");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {


                    comfirm.setTitle("Xác Nhận");
                    comfirm.setHeaderText("Xóa môn học: " + selectedSubject.getName());
                    comfirm.showAndWait().ifPresent(res -> {
                        if (res == ButtonType.OK) {
            //                   Xóa khóa ngoại của subject trong bảng semester_subject
                            Semester_SubjectDAO.getInstance().remove(selectedSubject.getId());
            //                   Xóa  subject trong bảng subject
                            SubjectDAO.getIntance().remove(selectedSubject);
                            System.out.println("Object deleted.");
                            try {
                                Navigator.getInstance().gotoListSubject();
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } else {
                    System.out.println("Delete operation canceled.");
                }
            });
        } else {
            comfirm.setTitle("Xác Nhận");
            comfirm.setHeaderText("Xóa môn học: " + selectedSubject.getName());
            comfirm.showAndWait().ifPresent(res -> {
                if (res == ButtonType.OK) {
                    SubjectDAO.getIntance().remove(selectedSubject);
                    System.out.println("Object deleted.");
                }
            });
        }
        Navigator.getInstance().gotoListSubject();
    }


    void showListCourses(){
        ArrayList<Course> courses = CourseDAO.getIntance().findAllCourse();
        ArrayList<String> listCourse = new ArrayList<>();

        for (Course course : courses) {
            listCourse.add(course.getName());
        }

        btnFindByCourse.setOnAction(event -> {
            int selectedIndex = btnFindByCourse.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < courses.size()) {
                courseSelected = courses.get(selectedIndex);
                semesters = SemesterDAO.getInstance().selectByCourseId(courses.get(selectedIndex).getId());
                System.out.println("Chọn class name: " + courses.get(selectedIndex).getName());
                this.callbtnListSem();
            }
        });
        btnFindByCourse.getItems().addAll(listCourse);
    }

    public void callbtnListSem(){
        btnFindBySemester.setOnAction(event -> {
            int selectedIndex = btnFindBySemester.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < semesters.size()) {
                semesterSelected = semesters.get(selectedIndex); // Cập nhật semesterSelected với đối tượng tương ứng
                System.out.println("Chọn semester name: " + semesterSelected.getName());
                listSubjects = Semester_SubjectDAO.getInstance().getListSubjectBySemesterID(semesterSelected.getId());
                System.out.println("Trong listSubjects có: " + listSubjects.size());

                subjects = FXCollections.observableList(listSubjects);
                tblSubject.setItems(subjects);

            }
        });

        // Tạo danh sách tên các semester để thêm vào nút btnListSemester
        ArrayList<String> listSemesterNames = new ArrayList<>();
        for (Semester semester : semesters) {
            listSemesterNames.add(semester.getName());
        }
        btnFindBySemester.getItems().clear();
        btnFindBySemester.getItems().addAll(listSemesterNames);
    }
    @FXML
    public void allSubject(ActionEvent actionEvent) throws IOException {
        Navigator.getInstance().gotoListSubject();
    }
    @FXML
    public void gotoBackListCourse(ActionEvent actionEvent) throws IOException {
        Navigator.getInstance().gotoCourse();
    }
    @FXML
    public void addSubject(ActionEvent actionEvent) {
        try {
            if (subjectStage != null && subjectStage.isShowing()) {
                // Nếu cửa sổ môn học đã tồn tại, đưa cửa sổ hiện tại lên phía trước
                subjectStage.toFront();
            } else {
                // Nếu cửa sổ môn học chưa tồn tại, tạo mới cửa sổ và hiển thị
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/anhvt/aptechmanagement/UI/admin/subject/addSubjectUI.fxml"));
                Parent root = loader.load();

                subjectStage = new Stage();
                subjectStage.setTitle("Thêm môn học");
                subjectStage.setScene(new Scene(root));

                subjectStage.setOnCloseRequest(t -> {
                    subjectStage = null;
                });

                AddSubjectController controller = loader.getController();
                controller.setStage(subjectStage); // Đặt đối tượng Stage vào controller

                subjectStage.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void update(ActionEvent actionEvent) {
    }
}
