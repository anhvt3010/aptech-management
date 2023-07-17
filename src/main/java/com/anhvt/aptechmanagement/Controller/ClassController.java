package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClassController extends SideBarController implements Initializable {
    @FXML
    private TableView<Classes> tblClass;

    @FXML
    private TableColumn<Classes, Integer> tcID;

    @FXML
    private TableColumn<Classes, Integer> tcLimit;

    @FXML
    private TableColumn<Classes, String> tcName;

    @FXML
    private TableColumn<Classes, String> tcSRO;

    @FXML
    private TableColumn<Classes, String> tcSubject;

    @FXML
    private TableColumn<Classes, String> tcType;

    ObservableList<Classes> classes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classes = FXCollections.observableArrayList();
        ArrayList<Classes> list = ClassDAO.getIntance().findAll();
        classes.addAll(list);

        tblClass.setItems(classes);

        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcLimit.setCellValueFactory(new PropertyValueFactory<>("limit"));
        tcType.setCellValueFactory(cellData -> {
            Byte type = cellData.getValue().getType();
            String typeValue;
            if (type == 1) {
                typeValue = "SÁNG";
            } else if (type == 2) {
                typeValue = "CHIỀU";
            } else {
                typeValue = "TỐI";
            }
            return new SimpleStringProperty(typeValue);
        });
    }
}
