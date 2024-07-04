package program.youtube;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MyProfileController {

    @FXML
    private Button createbtn;

    @FXML
    private HBox gaminhbox;

    @FXML
    private HBox historyhbox;

    @FXML
    private HBox homehbox;

    @FXML
    private HBox likedvideoshbox;

    @FXML
    private Label lname;

    @FXML
    private ImageView menu;

    @FXML
    private VBox middlepage;

    @FXML
    private Label name;

    @FXML
    private ImageView notification;

    @FXML
    private HBox playlisthbox;

    @FXML
    private HBox premiumhbox;

    @FXML
    private ImageView profile;

    @FXML
    private HBox reporthbox;

    @FXML
    private HBox settinghbox;

    @FXML
    private HBox subscriptionhbox;

    @FXML
    private HBox trendinghbox;

    @FXML
    private HBox uploadvideohbox;

    @FXML
    private Label username;

    @FXML
    private GridPane videoGrid;

    @FXML
    private HBox watchlaterhbox;

    @FXML
    private HBox yourchannelhbox;

    @FXML
    private HBox yourvideoshbox;

    @FXML
    void createbtn(ActionEvent event) {
        try {
            System.out.println("Trying to load FXML...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("upload.fxml"));
            Parent root = fxmlLoader.load();
            System.out.println("FXML loaded successfully.");

            Stage stage = new Stage();
            stage.setTitle("Upload your video");
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Stage shown.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gaminhbox(MouseEvent event) {

    }

    @FXML
    void historyhbox(MouseEvent event) {

    }

    @FXML
    void homehbox(MouseEvent event) {

    }

    @FXML
    void likedvideoshbox(MouseEvent event) {

    }

    @FXML
    void menu(MouseEvent event) {

    }

    @FXML
    void middlepage(MouseEvent event) {

    }

    @FXML
    void notification(MouseEvent event) {

    }

    @FXML
    void playlisthbox(MouseEvent event) {

    }

    @FXML
    void premiumhbox(MouseEvent event) {

    }

    @FXML
    void profile(MouseEvent event) {

    }

    @FXML
    void reporthbox(MouseEvent event) {

    }

    @FXML
    void settinghbox(MouseEvent event) {

    }

    @FXML
    void subscriptionhbox(MouseEvent event) {

    }

    @FXML
    void trendinghbox(MouseEvent event) {

    }

    @FXML
    void uploadvideohbox(MouseEvent event) {
        try {
            System.out.println("Trying to load FXML...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("upload.fxml"));
            Parent root = fxmlLoader.load();
            System.out.println("FXML loaded successfully.");

            Stage stage = new Stage();
            stage.setTitle("Upload your video");
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Stage shown.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void videoGrid(MouseEvent event) {

    }

    @FXML
    void watchlaterhbox(MouseEvent event) {

    }

    @FXML
    void yourchannelhbox(MouseEvent event) {

    }

    @FXML
    void yourvideoshbox(MouseEvent event) {

    }

}
