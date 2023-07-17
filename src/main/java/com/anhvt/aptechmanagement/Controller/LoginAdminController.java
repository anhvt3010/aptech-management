package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.ProfileDAO;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
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
            Student st = ProfileDAO.getIntance().getAccountByEmail(email);
            if(st != null){
                if(st.getPassword().equals(pass)){
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
