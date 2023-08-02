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

    opens com.anhvt.aptechmanagement to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.student to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.user to javafx.fxml;

//    ****
    opens com.anhvt.aptechmanagement.Model to javafx.base;
    opens com.anhvt.aptechmanagement.Property to javafx.base;

    exports com.anhvt.aptechmanagement;
    exports com.anhvt.aptechmanagement.Controller;
    exports com.anhvt.aptechmanagement.Controller.student to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.user.ScoreController to javafx.fxml;
    opens com.anhvt.aptechmanagement.Controller.user.ScheduleController to javafx.fxml;
}