package com.anhvt.aptechmanagement.Utils;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class WindowManager {
    private static List<Stage> openStages = new ArrayList<>();

    public static void addStage(Stage stage) {
        openStages.add(stage);
    }

    public static void removeStage(Stage stage) {
        openStages.remove(stage);
    }

    public static void closeAllStages() {
        for (Stage stage : openStages) {
            stage.close();
        }
        openStages.clear();
    }

}
