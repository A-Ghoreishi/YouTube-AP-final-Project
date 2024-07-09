package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class ThumbnailController {
    @FXML
    private Label channelName;

    @FXML
    private Label date;

    @FXML
    private ImageView profile;

    @FXML
    private ImageView thumbnail;

    @FXML
    private Label title;

    @FXML
    private Label views;

    private Video video;
    private int videoId;

    @FXML
    void profile(MouseEvent event) {
        // Handle profile click
    }

    public void setData(Video video) {

        try {
            Image image = new Image(getClass().getResourceAsStream(video.getThumbsrc()));
            thumbnail.setImage(image);

            Image image1 = new Image(getClass().getResourceAsStream(video.getProfilesrc()));
            profile.setImage(image1);

            title.setText(video.getName());
            channelName.setText(video.getChannel());
            views.setText(video.getViews());
            date.setText(video.getDate());
        } catch (Exception e) {
            // Handle the exception (print stack trace or log message)
            e.printStackTrace();
        }
    }
}
