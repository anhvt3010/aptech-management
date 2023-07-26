package com.anhvt.aptechmanagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomSidebarExample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Tạo các nút trong sidebar
        Button homeButton = createSidebarButton("Home");
        Button dashboardButton = createSidebarButton("Dashboard");
        Button settingsButton = createSidebarButton("Settings");

        // Thêm các nút vào sidebar
        root.getChildren().addAll(homeButton, dashboardButton, settingsButton);

        // Thêm nội dung chính vào bên phải sidebar (giả sử là một Pane hoặc bất kỳ thành phần giao diện nào khác)
        // Pane mainContent = ... // Khởi tạo và cấu hình nội dung chính của bạn
        // root.getChildren().add(mainContent);

        Scene scene = new Scene(root, 300, 400, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Custom Sidebar Example");
        primaryStage.show();
    }
    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(120);
        button.setPrefHeight(40);
        button.setStyle("-fx-background-color: #a0a0a0;");
        button.setTextFill(Color.WHITE);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
