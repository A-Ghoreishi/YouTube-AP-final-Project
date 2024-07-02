module program.youtube {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media; // Add this line for JavaFX Media module

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens program.youtube to javafx.fxml;
    exports program.youtube;
}
