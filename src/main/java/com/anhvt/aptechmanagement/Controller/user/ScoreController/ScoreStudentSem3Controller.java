package com.anhvt.aptechmanagement.Controller.user.ScoreController;

import com.anhvt.aptechmanagement.Controller.user.SidebarUserController;
import com.anhvt.aptechmanagement.DAO.ScoreDAO;
import com.anhvt.aptechmanagement.DAO.Semester_SubjectDAO;
import com.anhvt.aptechmanagement.Model.Score;
import com.anhvt.aptechmanagement.Model.Subject;
import com.anhvt.aptechmanagement.Navigator;
import com.anhvt.aptechmanagement.Utils.Session;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreStudentSem3Controller  extends SidebarUserController implements Initializable {
    @FXML
    private Button btnKi1;

    @FXML
    private Button btnKi2;

    @FXML
    private Button btnKi3;

    @FXML
    private Button btnKi4;

    @FXML
    private TableView<Score> tblScore;

    @FXML
    private TableColumn<Score, Integer> tcMaxScore;

    @FXML
    private TableColumn<Score, Float> tcPercent;

    @FXML
    private TableColumn<Score, Integer> tcScore;

    @FXML
    private TableColumn<Score, String> tcStatus;

    @FXML
    private TableColumn<Score, String> tcSubject;

    @FXML
    private TableColumn<Score, String> tcType;

    @FXML
    private Text txtHelloStudent;

    @FXML
    private Text txtOverall;

    private final ArrayList<Score> listScore = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHelloStudent.setText("Xin chào " + Session.getAttribute().getLastName() );

        for (Integer integer : Semester_SubjectDAO.getInstance().selectBySemester(3)) {
            ArrayList<Score> list = ScoreDAO.getInstance().selectByIdStudentAndIdSubject(Session.getAttribute().getId(), integer);
            listScore.addAll(list);
        }

        ObservableList<Score> scoreList = FXCollections.observableList(listScore);
        tblScore.setItems(scoreList);

        tcSubject.setCellValueFactory(cellData -> {
            Subject subject = cellData.getValue().getSubject();
            return new SimpleStringProperty(subject.getCode());
        });

        tcType.setCellValueFactory(cellData -> {
            Byte type = cellData.getValue().getType();
            if(type == 1){
                return new SimpleStringProperty("Lý Thuyết");
            } else {
                return new SimpleStringProperty("Thực Hành");
            }
        });

        tcScore.setCellValueFactory(cellData -> {
            int scoreValue = cellData.getValue().getScore();
            return new SimpleIntegerProperty(scoreValue).asObject();
        });
        tcMaxScore.setCellValueFactory(new PropertyValueFactory<>("score_max"));

        tcPercent.setCellValueFactory(cellData -> {
            Score score = cellData.getValue();
            float percentValue = (float) (score.getScore() * 100.0 / score.getScore_max());
            return new SimpleFloatProperty(percentValue).asObject();
        });

//        tcPercent.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        tcStatus.setCellValueFactory(cellData -> {
            float percent = cellData.getValue().getScore() * 100.0f / cellData.getValue().getScore_max();
            if (percent < 40) {
                return new SimpleStringProperty("RE-EXAM");
            } else if (percent < 60) {
                return new SimpleStringProperty("PASS");
            } else if (percent < 70) {
                return new SimpleStringProperty("CREDIT");
            } else {
                return new SimpleStringProperty("DISTINCTION");
            }
        });

    }

    @FXML
    void showKi1(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScoreSem1();
    }

    @FXML
    void showKi2(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScoreSem2();
    }

    @FXML
    void showKi3(ActionEvent event) throws IOException {
//        Navigator.getInstance().gotoScoreSem3();
    }

    @FXML
    void showKi4(ActionEvent event) throws IOException {
        Navigator.getInstance().gotoScoreSem4();
    }
}
