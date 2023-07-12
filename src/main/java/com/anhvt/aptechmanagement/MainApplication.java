package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.StudentDAO;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator.getInstance().setStage(stage);
        Navigator.getInstance().gotoSelect();
    }

    public static void main(String[] args) {
        System.out.println(StudentDAO.getIntance().findAll().size());
        launch();
    }
}