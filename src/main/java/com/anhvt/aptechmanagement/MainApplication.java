package com.anhvt.aptechmanagement;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator.getInstance().setStage(stage);
        Navigator.getInstance().gotoClass();
    }

    public static void main(String[] args) {
        launch();
    }
}