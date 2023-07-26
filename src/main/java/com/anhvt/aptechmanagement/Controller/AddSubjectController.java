package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.SubjectDAO;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Validation.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {
    private Stage stage;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtCode;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private CheckBox txtType;
    @FXML
    private ChoiceBox<String> txtFormat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<Integer, String> mapFormat = new HashMap<>();
        mapFormat.put(0, "Lý Thuyết");
        mapFormat.put(1, "Lý Thuyết và Thực Hành");
        mapFormat.put(2, "Đồ Án");

        txtFormat.getItems().addAll(mapFormat.values());

    }
    @FXML
    void quit(ActionEvent event) {
        stage.close();
    }

    @FXML
    void saveSubject(ActionEvent event) throws IOException {
        // Lấy thông tin từ giao diện
        String code = txtCode.getText().trim();
        String name = txtName.getText().trim();
        String numSessionsText = txtNumber.getText().trim();
        String description = txtDescription.getText().trim();
        String format = txtFormat.getValue();
        boolean typeSelected = txtType.isSelected();

        // Kiểm tra dữ liệu nhập vào
        if (Validation.isNullOrEmpty(code) || Validation.isNullOrEmpty(name) || Validation.isNullOrEmpty(numSessionsText) || format == null) {
            AlertUtil.showErrorAlert("Lỗi", "Dữ liệu không hợp lệ", "Vui lòng điền đầy đủ thông tin môn học.");
            return;
        }

        // Chuyển đổi dữ liệu và tạo đối tượng Subject
        int numSessions = Integer.parseInt(numSessionsText);
        byte examFormat = (byte) (format.equals("LT") ? 0 : format.equals("LT/TH") ? 1 : 2);
        byte type = typeSelected ? (byte) 1 : (byte) 0;

        Subject subject = new Subject();
        subject.setCode(code);
        subject.setName(name);
        subject.setNumber_of_sessions(numSessions);
        subject.setDescription(description);
        subject.setExam_format(examFormat);
        subject.setType(type);

        // Lưu đối tượng Subject
        SubjectDAO.getIntance().insert(subject);

        // Hiển thị thông báo thành công
        AlertUtil.showInforEAlert("Thông Báo", "Thêm môn học thành công", "");

        // Đóng cửa sổ sau khi hiển thị thông báo
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
