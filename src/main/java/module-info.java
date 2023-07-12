module com.anhvt.aptechmanagement {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
//                requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.anhvt.aptechmanagement to javafx.fxml;
    exports com.anhvt.aptechmanagement;
    exports com.anhvt.aptechmanagement.Controller;
    opens com.anhvt.aptechmanagement.Controller to javafx.fxml;
}