package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.NotificationDAO;
import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.DAO.Student_LearnDAO;
import com.anhvt.aptechmanagement.Model.Notification;
import com.anhvt.aptechmanagement.Property.CourseProperty;
import com.anhvt.aptechmanagement.Property.NotificationProperty;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationController extends SideBarController implements Initializable {
    @FXML
    public Button btnUpdate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnSave;

    @FXML
    private TextArea txtContent;
    @FXML
    private ChoiceBox<String> txtStatus;
    @FXML
    private TextField txtStudent;
    @FXML
    private TextField txtTitle;

    @FXML
    private TableView<NotificationProperty> tblNotification;

    @FXML
    private TableColumn<NotificationProperty, String> tcContent;

    @FXML
    private TableColumn<NotificationProperty, Integer> tcID;

    @FXML
    private TableColumn<NotificationProperty, String> tcStatus;

    @FXML
    private TableColumn<NotificationProperty, String> tcTitle;

    ObservableList<NotificationProperty> notificationProperties;

    NotificationProperty selectedNotification = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notificationProperties = FXCollections.observableList(NotificationDAO.getInstance().findAllProperty());
        tblNotification.setItems(notificationProperties);

        tcID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        tcContent.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tblNotification.setRowFactory(tv -> {
            TableRow<NotificationProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedNotification = row.getItem();
                    handle_selectedCourse(selectedNotification);
                }
            });
            return row;
        });

        this.turn_off_editable();
    }

    private void handle_selectedCourse(NotificationProperty selectedNotification) {
        txtStudent.setText(Student_LearnDAO.getInstance().selectByStudentID(selectedNotification.getStudent().getId()).getStudent_code());
        txtTitle.setText(selectedNotification.getTitle());
        txtContent.setText(selectedNotification.getContent());
        txtStatus.setValue(tcStatus.getCellData(selectedNotification));
    }

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {
        txtStudent.setDisable(true);
        txtTitle.setEditable(true);
        txtContent.setEditable(true);
        txtStatus.setDisable(false);
        btnSave.setDisable(false);
    }

    void turn_off_editable(){
        txtStudent.setEditable(false);
        txtTitle.setEditable(false);
        txtContent.setEditable(false);
        txtStatus.setDisable(true);
        btnSave.setDisable(true);
    }

}
