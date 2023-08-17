package com.anhvt.aptechmanagement.Controller.admin;

import com.anhvt.aptechmanagement.Controller.SideBarController;
import com.anhvt.aptechmanagement.DAO.FormDAO;
import com.anhvt.aptechmanagement.Model.Form;
import com.anhvt.aptechmanagement.Model.Student;
import com.anhvt.aptechmanagement.Property.ExamProperty;
import com.anhvt.aptechmanagement.Property.FormProperty;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import com.anhvt.aptechmanagement.Utils.SearchUtils;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormController extends SideBarController implements Initializable {
    @FXML
    public TextField txtSearch;
    @FXML
    public TextField txtAddLink;
    @FXML
    public Button btnAddSave;
    @FXML
    public CheckBox checkboxAddStatus;
    @FXML
    public TextArea txtAddTitle;
    @FXML
    public TextField txtDetailLink;
    @FXML
    public CheckBox checkboxDetailStatus;
    @FXML
    public Button btnUpdateSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnAdd;
    @FXML
    public TextArea txtDetailTitle;
    @FXML
    public AnchorPane formAdd;
    @FXML
    private TableView<FormProperty> tblForm;
    @FXML
    private TableColumn<FormProperty, String> tcLink;
    @FXML
    private TableColumn<FormProperty, Integer> tcSTT;
    @FXML
    private TableColumn<FormProperty, String> tcStatus;
    @FXML
    private TableColumn<FormProperty, String> tcTitle;


    private ObservableList<FormProperty> formProperties;

    private FormProperty selectedFormProperty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formProperties = FXCollections.observableList(FormDAO.getInstance().findAllProperty());
        tblForm.setItems(formProperties);

        tcSTT.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        tcTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        tcLink.setCellValueFactory(cellData -> cellData.getValue().linkProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        txtAddTitle.textProperty().addListener((observable, oldValue, newValue) -> addSaveButtonStatus());
        txtAddLink.textProperty().addListener((observable, oldValue, newValue) -> addSaveButtonStatus());

        tblForm.setRowFactory(tv -> {
            TableRow<FormProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedFormProperty = row.getItem();
                    handle_selectedFormProperty(selectedFormProperty);
                    this.disableDetail();
                }
            });

            return row;
        });

        // search
        FilteredList<FormProperty> filteredForm = new FilteredList<>(formProperties, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredForm.setPredicate(form -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return form.getTitle().toLowerCase().contains(lowerCaseFilter);
            });
        });
        tblForm.setItems(filteredForm);

        this.disableDetail();
        btnUpdateSave.setDisable(true);
        btnAddSave.setDisable(true);
        formAdd.setVisible(false);

        this.deleteForm();

    }

    private void handle_selectedFormProperty(FormProperty selectedFormProperty) {
        txtDetailTitle.setText(selectedFormProperty.getTitle());
        txtDetailLink.setText(selectedFormProperty.getLink());
        if (selectedFormProperty.getStatus().equals("Đang HĐ")) {
            checkboxDetailStatus.setSelected(true);
        } else {
            checkboxDetailStatus.setSelected(false);
        }

    }

    @FXML
    public void add(ActionEvent actionEvent) {
        FormDAO.getInstance().insert(this.getObjectFormAdd());
        formProperties.setAll(FormDAO.getInstance().findAllProperty());
        AlertUtil.showInforEAlert("Thông Báo", "Thêm thành công !", "");
        this.clearText();
    }
    @FXML
    public void update(ActionEvent actionEvent) {
        FormDAO.getInstance().update(getObjectFormDetail());
        formProperties.setAll(FormDAO.getInstance().findAllProperty());
        AlertUtil.showInforEAlert("Thông Báo", "Sửa thông tin thành công !", "");


    }
    @FXML
    public void openUpdate(ActionEvent actionEvent) {
        this.enableDetail();
    }
    @FXML
    public void openAdd(ActionEvent actionEvent) {
        this.disableDetail();
        formAdd.setVisible(true);
        btnAdd.setDisable(true);
    }

    public Form getObjectFormDetail(){
        Form form = new Form();
        form.setId(selectedFormProperty.getId());
        form.setTitle(txtDetailTitle.getText());
        form.setLink(txtDetailLink.getText());
        form.setStatus((byte) (checkboxDetailStatus.isSelected()?1:0));
        return form;
    }

    public Form getObjectFormAdd(){
        Form form = new Form();
        form.setTitle(txtAddTitle.getText());
        form.setLink(txtAddLink.getText());
        form.setCreated(LocalDate.now());
        form.setStatus((byte) (checkboxAddStatus.isSelected()?1:0));
        return form;
    }

    private void updateSaveButtonStatus() {
        boolean allFieldsFilled = !txtDetailTitle.getText().isEmpty() &&
                !txtDetailLink.getText().isEmpty();
        btnUpdateSave.setDisable(!allFieldsFilled);
    }

    private void addSaveButtonStatus() {
        boolean allFieldsFilled = !txtAddTitle.getText().isEmpty() &&
                !txtAddLink.getText().isEmpty();
        btnAddSave.setDisable(!allFieldsFilled);
    }

    public void disableDetail(){
        txtDetailTitle.setDisable(true);
        txtDetailLink.setDisable(true);
        checkboxDetailStatus.setDisable(true);

        btnUpdateSave.setDisable(true);
    }

    public void enableDetail(){
        txtDetailTitle.setDisable(false);
        txtDetailLink.setDisable(false);
        checkboxDetailStatus.setDisable(false);

        txtDetailTitle.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
        txtDetailLink.textProperty().addListener((observable, oldValue, newValue) -> updateSaveButtonStatus());
    }
    private void deleteForm() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Xóa");

        deleteMenuItem.setOnAction(event -> {
            FormProperty selectedProperty = tblForm.getSelectionModel().getSelectedItem();
            if (selectedProperty != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận xóa");
                alert.setHeaderText("Bạn có chắc chắn muốn xóa?");

                ButtonType confirmButton = new ButtonType("Đồng ý", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(confirmButton, cancelButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == confirmButton) {
                    FormDAO.getInstance().removeByID(selectedProperty.getId());
                    formProperties.setAll(FormDAO.getInstance().findAllProperty());
                }
            }
        });

        contextMenu.getItems().add(deleteMenuItem);
        tblForm.setContextMenu(contextMenu);
    }

    public void clearText(){
        txtAddTitle.setText("");
        txtAddLink.setText("");
        checkboxAddStatus.setSelected(false);

    }

}
