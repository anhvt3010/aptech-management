package com.anhvt.aptechmanagement.Controller.user;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.NotificationDAO;
import com.anhvt.aptechmanagement.Model.Notification;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationStudentController extends SideBarController implements Initializable {
    @FXML
    private Hyperlink linkHocLai;

    @FXML
    private Hyperlink linkThiLai;

    @FXML
    private TableView<Notification> tblNoification;

    @FXML
    private TableColumn<Notification, String> tcContent;

    @FXML
    private TableColumn<Notification, Integer> tcSTT;

    @FXML
    private TableColumn<Notification, String> tcTitle;

    ObservableList<Notification> notifications;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHelloStudent.setText("Xin chào " + Session.getAttribute().getLastName() );

        notifications = FXCollections.observableList(NotificationDAO.getInstance().selectByStudentId(Session.getAttribute().getId()));

        tblNoification.setItems(notifications);
        tcSTT.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcContent.setCellValueFactory(new PropertyValueFactory<>("content"));

        linkThiLai.setOnAction(e -> {
            this.showForm("https://forms.gle/oyRL8NPMvC1hSESU6");
        });

        linkHocLai.setOnAction(e -> {
            this.showForm("https://forms.gle/eYenchwaCeqEqnxN9");
        });
    }
    public void showForm(String link){
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            try {
                java.net.URI uri = new java.net.URI(link);
                desktop.browse(uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
