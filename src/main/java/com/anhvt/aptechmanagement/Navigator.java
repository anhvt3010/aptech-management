package com.anhvt.aptechmanagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {

    private static Navigator navigator;
    private Stage stage;
    private Stage previousStage;

    // ----------view USER---------
    public static final String STUDENT_HOME = "UI/user/index.fxml";

    public static final String SELECT = "UI/select.fxml";
    public static final String ADMIN_HOME = "UI/admin/index.fxml";
    public static final String LOGIN_ADMIN = "UI/loginAdminUI.fxml";
    public static final String LOGIN_STUDENT= "UI/loginStudentUI.fxml";
    public static final String STUDENT_MANAGER = "UI/admin/student/listStudentUI.fxml";
    public static final String BROWSER = "UI/user/browser.fxml";

 // ------- URL Side bar Admin ---------------
    public static final String SIDE_BAR_STUDENT_ADMIN = "UI/admin/student/listStudentUI.fxml";
    public static final String SIDE_BAR_COURSE_ADMIN = "UI/admin/course/listCourseUI.fxml";
    public static final String SIDE_BAR_CLASS_ADMIN = "UI/admin/class/listClassUI.fxml";
    public static final String SIDE_BAR_SCHEDULE_ADMIN = "UI/admin/schedule/listScheduleUI.fxml";
    public static final String SIDE_BAR_TEST_ADMIN = "UI/admin/test/listTestUI.fxml";
    public static final String SIDE_BAR_DOCUMENT_ADMIN = "UI/admin/document/listDocumentUI.fxml";


// ------- URL Side bar User ---------------
    public static final String SIDE_BAR_INFORMATION_USER = "UI/user/informationUI.fxml";
    public static final String SIDE_BAR_SCRORE_USER = "UI/user/scoreUI.fxml";
    public static final String SIDE_BAR_SCHEDULE_USER = "UI/user/scheduleUI.fxml";
    public static final String SIDE_BAR_TEST_USER = "UI/user/testUI.fxml";
    public static final String SIDE_BAR_DOCUMENT_USER = "UI/user/documentUI.fxml";
    public static final String SIDE_BAR_NOTI_USER = "UI/user/notificationUI.fxml";

    private Navigator(){}

    public static Navigator getInstance(){
        if (navigator == null){
            navigator = new Navigator();
        }
        return navigator;
    }

    public void setStage(Stage stage){
        navigator.stage = stage;
    }

    public void gotoScene(String title, String URL) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(URL));
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        if(!stage.isShowing()){
            stage.show();
        }
    }

    public void gotoSelect() throws IOException {
        gotoScene("Bảng chọn", SELECT);
    }

    public void gotoLoginAdmin() throws IOException {
        gotoScene("Đăng nhập với tư cách quản li", LOGIN_ADMIN);
    }

    public void gotoLoginStudent() throws IOException {
        gotoScene("Đăng nhập với tư cách học viên", LOGIN_STUDENT);
    }

    // ------ Go to view Admin ----------

    public void gotoAdminHome() throws IOException {
        gotoScene("Trang chủ quản lí", ADMIN_HOME);
    }

    public void gotoStudentManager() throws IOException {
        gotoScene("Quản lí học viên", STUDENT_MANAGER);
    }

    // ------ Go to view User ----------

    public void gotoStudentHome() throws IOException {
        gotoScene("Trang chủ học viên", STUDENT_HOME);
    }
    public void gotoBrowser() {
        try {
            gotoScene("Trình duyệt nhanh", BROWSER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // --- Go to view Side Bar --- ADMIN
    public void gotoStudent() throws IOException {
        gotoScene("Quản lí học viên", SIDE_BAR_STUDENT_ADMIN);
    }
    public void gotoCourse() throws IOException {
        gotoScene("Quản lí khóa học", SIDE_BAR_COURSE_ADMIN);
    }
    public void gotoClass() throws IOException {
        gotoScene("Quản lí lớp học", SIDE_BAR_CLASS_ADMIN);
    }

    public void gotoSchedule() throws IOException {
        gotoScene("Quản lí lịch học", SIDE_BAR_SCHEDULE_ADMIN);
    }
    public void gotoTest() throws IOException {
        gotoScene("Quản lí lịch thi", SIDE_BAR_TEST_ADMIN);
    }
    public void gotoDocument() throws IOException {
        gotoScene("Quản lí đơn từ", SIDE_BAR_DOCUMENT_ADMIN);
    }


    // --- Go to view Side Bar --- USER
    public void gotoUserStudent() throws IOException {
        gotoScene("Hồ sơ cá nhân", SIDE_BAR_INFORMATION_USER);
    }
    public void gotoScore() throws IOException {
        gotoScene("Điểm thi", SIDE_BAR_SCRORE_USER);
    }
    public void gotoScheduleUser() throws IOException {
        gotoScene("Thời khóa biểu", SIDE_BAR_SCHEDULE_USER);
    }
    public void gotoTestUser() throws IOException {
        gotoScene("Lịch thi", SIDE_BAR_TEST_USER);
    }
    public void gotoDocumentUser() throws IOException {
        gotoScene("Đơn từ", SIDE_BAR_DOCUMENT_USER);
    }
    public void gotoNotification() throws IOException {
        gotoScene("Bảng thông báo", SIDE_BAR_NOTI_USER);
    }
}
