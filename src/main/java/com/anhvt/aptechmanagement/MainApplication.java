package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.Utils.PasswordEncoder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator.getInstance().setStage(stage);
        Navigator.getInstance().gotoSelect();
//        Navigator.getInstance().gotoTestSideBar();
    }

    public static void main(String[] args) {
        launch();
    }
}