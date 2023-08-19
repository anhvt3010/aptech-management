package com.anhvt.aptechmanagement.Controller.user;


import com.anhvt.aptechmanagement.Utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeStudentController extends SidebarUserController implements Initializable {
    @FXML
    private Label txtHello;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHello.setText("Xin chào " + Session.getAttribute().getLastName() );
    }
}
