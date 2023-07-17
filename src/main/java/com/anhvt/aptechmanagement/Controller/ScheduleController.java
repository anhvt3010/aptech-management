package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleController extends SideBarController implements Initializable {
    @FXML
    public Button btnSubmit;
    @FXML
    private MenuButton btnListClass;
    @FXML
    public Button btnUploadTKB;
    @FXML
    private ImageView demoSchedule;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Classes> classList = ClassDAO.getIntance().findAll();
        for (Classes className : classList) {
            MenuItem menuItem = new MenuItem(className.getName());
            menuItem.setOnAction(event -> {

            });
            btnListClass.getItems().add(menuItem);
        }
    }

    public void uploadTKB(ActionEvent actionEvent) {
        // Tạo hộp thoại chọn tệp tin
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn tệp tin ảnh để tải lên");

        // Mở hộp thoại chọn tệp tin và lấy tệp tin được chọn
        File selectedFile = fileChooser.showOpenDialog(btnUploadTKB.getScene().getWindow());

        // lấy đuôi file
        String fileName = selectedFile.getName();
        String format = null;
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            format =  fileName.substring(dotIndex + 1);
            System.out.println(format);
        }


        if (selectedFile != null) {

            // Thư mục đích để lưu tệp tin
            String destinationDirectory = "src/main/resources/com/anhvt/aptechmanagement/images/schedule";

            // Tạo thư mục đích
            File destinationFolder = new File(destinationDirectory);
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            try {
                // Đường dẫn đến tệp tin đích
                String destinationPath = destinationDirectory + "/" + selectedFile.getName();

                // Copy tệp tin vào thư mục đích
                Files.copy(selectedFile.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Lưu tệp tin thành công vào thư mục đích.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Lưu tệp tin thất bại.");
            }
        } else {
            System.out.println("Không có tệp tin ảnh được chọn.");
        }

    }
    @FXML
    public void submitUpload(ActionEvent actionEvent) {

    }
}
