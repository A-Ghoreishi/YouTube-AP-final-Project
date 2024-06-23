module program.youtube {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.json;

    opens program.youtube to javafx.fxml;
    exports program.youtube;
}