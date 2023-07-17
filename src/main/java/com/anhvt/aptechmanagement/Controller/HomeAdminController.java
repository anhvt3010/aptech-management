package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeAdminController implements Initializable {

    @FXML
    private Button btnStudent;

    @FXML
    LineChart<String, Number> chartNumber;

    @FXML
    BarChart<String, Number> barChart ;

    @FXML
    void showStudent(ActionEvent event) throws IOException {
        Navigator.getInstance().showListStudent();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Data<String, Number> jan = new XYChart.Data<>("Jan", 100);
        XYChart.Data<String, Number> feb = new XYChart.Data<>("Feb", 200);
        XYChart.Data<String, Number> mar = new XYChart.Data<>("Mar", 150);
        XYChart.Data<String, Number> apr = new XYChart.Data<>("Apr", 180);

        XYChart.Series<String, Number> aaa = new XYChart.Series<>();
        XYChart.Data<String, Number> a = new XYChart.Data<>("Jan", 120);
        XYChart.Data<String, Number> b = new XYChart.Data<>("Feb", 170);
        XYChart.Data<String, Number> c = new XYChart.Data<>("Mar", 150);
        XYChart.Data<String, Number> d = new XYChart.Data<>("Apr", 210);

        aaa.getData().addAll(a, b, c, d);
        series.getData().addAll(jan, apr, feb, mar);

        chartNumber.setTitle("Thu nhập hàng tháng");

        chartNumber.getData().add(series);
        chartNumber.getData().add(aaa);

        XYChart.Series<String, Number> barchart = new XYChart.Series<>();
        XYChart.Data<String, Number> aa = new XYChart.Data<>("Jan", 120);
        XYChart.Data<String, Number> bb = new XYChart.Data<>("Feb", 170);
        XYChart.Data<String, Number> cc = new XYChart.Data<>("Mar", 150);
        XYChart.Data<String, Number> dd = new XYChart.Data<>("Apr", 210);

        barchart.getData().addAll(aa, bb, cc, dd);

        barChart.setTitle("Thu nhập hàng tháng");

        barChart.getData().add(barchart);

    }
}