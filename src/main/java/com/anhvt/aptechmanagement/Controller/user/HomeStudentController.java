package com.anhvt.aptechmanagement.Controller.user;


import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeStudentController extends SideBarController implements Initializable {
    @FXML
    private Label txtHello;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHello.setText("Xin chào " + Session.getAttribute().getLastName() );
    }
}
