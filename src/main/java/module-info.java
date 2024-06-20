module program.final_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens program.final_project to javafx.fxml;
    exports program.final_project;
}