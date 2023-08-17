module com.anhvt.aptechmanagement {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
//                requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires jbcrypt;
    requires java.desktop;
    requires javafx.web;
    requires org.apache.logging.log4j;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires fontawesomefx;

    opens com.anhvt.aptechmanagement to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.admin.student to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.user to javafx.fxml;

//    ****
    opens com.anhvt.aptechmanagement.Model to javafx.base;
    opens com.anhvt.aptechmanagement.Property to javafx.base;

    exports com.anhvt.aptechmanagement;
    exports com.anhvt.aptechmanagement.Controller;
    opens com.anhvt.aptechmanagement.Controller to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.user.ScoreController to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.user.ScheduleController to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.classes;
    opens com.anhvt.aptechmanagement.Controller.admin.classes to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.course;
    opens com.anhvt.aptechmanagement.Controller.admin.course to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.exam;
    opens com.anhvt.aptechmanagement.Controller.admin.exam to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.notification;
    opens com.anhvt.aptechmanagement.Controller.admin.notification to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.score;
    opens com.anhvt.aptechmanagement.Controller.admin.score to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.semester;
    opens com.anhvt.aptechmanagement.Controller.admin.semester to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.subject;
    opens com.anhvt.aptechmanagement.Controller.admin.subject to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin;
    opens com.anhvt.aptechmanagement.Controller.admin to javafx.fxml;
    exports com.anhvt.aptechmanagement.Controller.admin.student;
    exports com.anhvt.aptechmanagement.Controller.user;
}