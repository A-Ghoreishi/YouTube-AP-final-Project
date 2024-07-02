package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Playlist {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ImageView backward;

    @FXML
    void backward(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("theYoutube.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
