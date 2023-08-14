package com.anhvt.aptechmanagement.Controller;

import com.anhvt.aptechmanagement.DAO.SubjectDAO;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Utils.AlertUtil;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class AddCourseController implements Initializable {
    @FXML
    public Button btnAddSubjectToSemester;
    @FXML
    public TextArea txtListSubjectAddToSemester;
    @FXML
    public Button btnAddNewSubject;
    @FXML
    public Button btnSaveSemester;
    @FXML
    public MaterialIconView iconCheckSem1;
    @FXML
    private RadioButton btnAddStatus;

    @FXML
    private Button btnSem1;    @FXML
    private Button btnSem2;    @FXML
    private Button btnSem3;    @FXML
    private Button btnSem4;    @FXML
    private Button btnSem5;    @FXML
    private Button btnSem6;    @FXML
    private Button btnSem7;    @FXML
    private Button btnSem8;

    @FXML
    private ChoiceBox<String> choiceNumSemester;

    @FXML
    private TableView<Subject> tblSubject;    @FXML
    private TableColumn<Subject, String> tcSubCode;    @FXML
    private TableColumn<Subject, String> tcSubFormat;    @FXML
    private TableColumn<Subject, Integer> tcSubID;    @FXML
    private TableColumn<Subject, String> tcSubName;    @FXML
    private TableColumn<Subject, Integer> tcSubNum;

    @FXML
    private TextField txtAddName;    @FXML
    private Text txtNameFile;    @FXML
    private Text txtNameSemester;

    @FXML
    private TextField txtDetailCode;    @FXML
    private TextArea txtDetailDescription;    @FXML
    private TextField txtDetailName;    @FXML
    private TextField txtDetailNum;    @FXML
    private TextField txtDetailType;

    private Stage stageAddCourse;
    ObservableList<Subject> subjects;
    ArrayList<Subject> subjectsInsertSemester = new ArrayList<>();
    Subject selectedSubject;

    int selectedSemesterNumber;

    private Button buttonSemClick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjects = FXCollections.observableList(SubjectDAO.getIntance().findAll());
        tblSubject.setItems(subjects);

        tcSubID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSubName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcSubCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tcSubNum.setCellValueFactory(new PropertyValueFactory<>("number_of_sessions"));
        tcSubFormat.setCellValueFactory(cellData -> {
            Byte value = cellData.getValue().getExam_format();
            if(value == 0){
                return new SimpleStringProperty("LT");
            } else if(value == 1){
                return new SimpleStringProperty("LT/TH");
            } else {
                return new SimpleStringProperty("Project");
            }
        });

        this.disable_editable();
        tblSubject.setRowFactory(tv -> {
            TableRow<Subject> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                btnAddSubjectToSemester.setDisable(false);
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedSubject = row.getItem();
                    this.showDetailSubject();
                }
            });
            return row;
        });

        btnAddSubjectToSemester.setDisable(true);
        this.showChoiceSemesterNumber();
        this.disable_ButtonSemester();


    }

    private void showDetailSubject(){
        txtDetailName.setText(selectedSubject.getName());
        txtDetailCode.setText(selectedSubject.getCode());
        txtDetailNum.setText(selectedSubject.getNumber_of_sessions()+"");
        txtDetailDescription.setText(selectedSubject.getDescription());
        txtDetailType.setText(selectedSubject.getType()==0?"Không BB":"BB");
    }

    @FXML
    void quit(ActionEvent event) {

    }

    private void showChoiceSemesterNumber(){
        Map<Integer, String> mapFormat = new HashMap<>();
        mapFormat.put(1, "1 Kì");
        mapFormat.put(2, "2 Kì");
        mapFormat.put(3, "3 Kì");
        mapFormat.put(4, "4 Kì");
        mapFormat.put(5, "5 Kì");
        mapFormat.put(6, "6 Kì");
        mapFormat.put(7, "7 Kì");
        mapFormat.put(8, "8 Kì");

        choiceNumSemester.getItems().addAll(mapFormat.values());

        choiceNumSemester.setOnAction(event -> {
            this.disable_ButtonSemester();
            int selectedIndex = choiceNumSemester.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < mapFormat.size()) {
                selectedSemesterNumber = selectedIndex + 1;
                this.enableButtonsAfterIndex();
                System.out.println("num semester: " + selectedSemesterNumber);
            }
        });
    }

    public void disable_ButtonSemester() {
        btnSem1.setVisible(false);
        btnSem2.setVisible(false);
        btnSem3.setVisible(false);
        btnSem4.setVisible(false);
        btnSem5.setVisible(false);
        btnSem6.setVisible(false);
        btnSem7.setVisible(false);
        btnSem8.setVisible(false);
    }
    private void enableButtonsAfterIndex() {
        for (int i = 1; i <= selectedSemesterNumber; i++) {
            switch (i) {
                case 1 -> {
                    btnSem1.setVisible(true);
                    btnSem1.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem1);
                    });
                }
                case 2 -> {
                    btnSem2.setVisible(true);
                    btnSem2.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem2);
                    });
                }
                case 3 -> {
                    btnSem3.setVisible(true);
                    btnSem3.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem3);
                    });
                }
                case 4 -> {
                    btnSem4.setVisible(true);
                    btnSem4.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem4);
                    });
                }
                case 5 -> {
                    btnSem5.setVisible(true);
                    btnSem5.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem5);
                    });
                }
                case 6 -> {
                    btnSem6.setVisible(true);
                    btnSem6.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem6);
                    });
                }
                case 7 -> {
                    btnSem7.setVisible(true);
                    btnSem7.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem7);
                    });
                }
                case 8 -> {
                    btnSem8.setVisible(true);
                    btnSem8.setOnAction(actionEvent -> {
                        txtListSubjectAddToSemester.setText("");
                        this.addSemester(btnSem8);
                    });
                }
                default -> {
                }
            }
        }
    }

    @FXML
    public void addSubjectToSemester(ActionEvent actionEvent) {
        String oldText;
        String updatedText;

        if (subjectsInsertSemester != null) {
            boolean isSubjectAlreadyAdded = false;

            for (Subject sj : subjectsInsertSemester) {
                if (sj == selectedSubject) {
                    isSubjectAlreadyAdded = true;
                    break;
                }
            }

            if (!isSubjectAlreadyAdded) {
                subjectsInsertSemester.add(selectedSubject);

                if (!txtListSubjectAddToSemester.getText().isEmpty()) {
                    oldText = txtListSubjectAddToSemester.getText();
                    updatedText = oldText + "\n" + selectedSubject.getCode() + " - " + selectedSubject.getName();
                    txtListSubjectAddToSemester.setText(updatedText);
                } else {
                    txtListSubjectAddToSemester.setText(selectedSubject.getCode() + " - " + selectedSubject.getName());
                }
            } else {
                AlertUtil.showErrorAlert("Lỗi", "Đã có môn học này rồi", "");
            }
        } else {
            subjectsInsertSemester = new ArrayList<>();
            subjectsInsertSemester.add(selectedSubject);
            txtListSubjectAddToSemester.setText(selectedSubject.getName());
        }

    }

    private void addSemester(Button btn){
        txtNameSemester.setText(btn.getText());
        btnSaveSemester.setOnAction(actionEvent -> {
            btn.setStyle("-fx-background-color: #339c4f; -fx-text-fill: white;");
        });
    }

    private void disable_editable(){
        txtDetailName.setEditable(false);
        txtDetailCode.setEditable(false);
        txtDetailNum.setEditable(false);
        txtDetailDescription.setEditable(false);
        txtDetailType.setEditable(false);
    }


    public void setStageAddCourse(Stage stageAddCourse) {
        this.stageAddCourse = stageAddCourse;
    }
    @FXML
    public void addNewSubject(ActionEvent actionEvent) {
    }
    @FXML
    public void saveSemester(ActionEvent actionEvent) {
    }
}
