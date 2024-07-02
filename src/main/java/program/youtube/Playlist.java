package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Playlist implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;


    @FXML
    private ImageView backward;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setImage(image1, "/images/pro1.png");
        setImage(image2, "/images/thumb2.jpg");
        setImage(image3, "/images/thumb1.jpg");
    }

    private void setImage(ImageView imageView, String path) {
        URL imageUrl = getClass().getResource(path);
        if (imageUrl != null) {
            imageView.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            System.err.println("Could not find image at path: " + path);
        }
    }

    @FXML
    void backward(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("theYoutube.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
