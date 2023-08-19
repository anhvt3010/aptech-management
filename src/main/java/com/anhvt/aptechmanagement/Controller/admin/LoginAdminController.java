package com.anhvt.aptechmanagement.Controller.admin;

import com.anhvt.aptechmanagement.DAO.SroDAO;
import com.anhvt.aptechmanagement.DAO.StudentDAO;
import com.anhvt.aptechmanagement.Model.Staff;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.PasswordEncoder;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginAdminController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void login(ActionEvent event) throws IOException {
        String email = txtUsername.getText();
        String pass = txtPassword.getText();

        if(email.isEmpty() || pass.isEmpty()){
            AlertUtil.showErrorAlert("Đăng nhập thất bại",
                                "Email hoặc mật khẩu trống !",
                                "Vui lòng điền đầy đủ");
        } else {
            Staff st = SroDAO.getInstance().getAccountByEmail(email);
            if(st != null){
                if(PasswordEncoder.verifyPassword(pass,st.getPassword())){
                    Session.setAttribute(st);
                    Navigator.getInstance().gotoAdminHome();
                } else {
                    AlertUtil.showErrorAlert("Đăng nhập thất bại",
                            "Mật khẩu sai !",
                            "Vui lòng điền lại mật khẩu");
                }
            } else {
                AlertUtil.showErrorAlert("Đăng nhập thất bại",
                        "Email không tồn tại !",
                        "Vui lòng điền lại email");
            }

        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoSelect();
    }

}
