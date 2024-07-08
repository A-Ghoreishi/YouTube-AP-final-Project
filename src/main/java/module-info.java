module program.youtube {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media; // Add this line for JavaFX Media module

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.json;
    requires com.fasterxml.jackson.databind;

    opens program.youtube to javafx.fxml;
    exports program.youtube;
}
