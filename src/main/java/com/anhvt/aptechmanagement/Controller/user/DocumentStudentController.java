package com.anhvt.aptechmanagement.Controller.user;

import com.anhvt.aptechmanagement.DAO.FormDAO;
import com.anhvt.aptechmanagement.Model.Form;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentStudentController extends SidebarUserController implements Initializable {
    @FXML
    private TableView<Form> tblForm;

    @FXML
    private TableColumn<Form, Hyperlink> tcContent;

    @FXML
    private TableColumn<Form, String> tcTitle;

    private ObservableList<Form> forms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forms = FXCollections.observableList(FormDAO.getInstance().findAll());

        tblForm.setItems(forms);
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcContent.setCellValueFactory(cellData -> {
            String link = cellData.getValue().getLink();
            Hyperlink hyperlink = new Hyperlink(link);
            hyperlink.setOnAction(event -> showForm(link));
            return new SimpleObjectProperty<>(hyperlink);
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
