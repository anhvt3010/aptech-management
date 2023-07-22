package com.anhvt.aptechmanagement.Controller.user;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentStudentController extends SideBarController implements Initializable {
    @FXML
    private Hyperlink linkBaoLuu;

    @FXML
    private Hyperlink linkChuyenLop;

    @FXML
    private Hyperlink linkHoanThi;

    @FXML
    private Hyperlink linkHocDuThinh;

    @FXML
    private Hyperlink linkHocLai;

    @FXML
    private Hyperlink linkHocSongSong;

    @FXML
    private Hyperlink linkNghiHoc;

    @FXML
    private Hyperlink linkPhucKhao;

    @FXML
    private Hyperlink linkThiLai;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linkThiLai.setOnAction(e -> {
            this.showForm("https://forms.gle/oyRL8NPMvC1hSESU6");
        });

        linkHoanThi.setOnAction(e -> {
            this.showForm("https://forms.gle/SCYCjFqhgq9iYDsX7");
        });

        linkBaoLuu.setOnAction(e -> {
            this.showForm("https://forms.gle/Y5ZwKh6LZNnd7mb5A");
        });

        linkChuyenLop.setOnAction(e -> {
            this.showForm("https://forms.gle/8v5RxCqe8Qvh1Eqh7");
        });

        linkHocDuThinh.setOnAction(e -> {
            this.showForm("https://forms.gle/h78P6o4VYAnNXMw67");
        });

        linkHocLai.setOnAction(e -> {
            this.showForm("https://forms.gle/eYenchwaCeqEqnxN9");
        });

        linkHocSongSong.setOnAction(e -> {
            this.showForm("https://forms.gle/EMHcegzuCuBDe5216");
        });

        linkNghiHoc.setOnAction(e -> {
            this.showForm("https://forms.gle/rYRjPa7Un9p7gZqU6");
        });

        linkPhucKhao.setOnAction(e -> {
            this.showForm("https://forms.gle/beG25JQDfCKkyjCG6");
        });

    }

    public void showForm(String link){
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (desktop.isDesktopSupported() && desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
            try {
                java.net.URI uri = new java.net.URI(link);
                desktop.browse(uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


}
