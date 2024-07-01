module program.youtube {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens program.youtube to javafx.fxml;
    exports program.youtube;
//    exports model;
//    opens model to javafx.fxml;
}