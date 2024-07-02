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
//        try {
//            InputStream thumbStream = getClass().getResourceAsStream(video.getThumbsrc());
//            if (thumbStream == null) {
//                logger.severe("Thumbnail resource not found: " + video.getThumbsrc());
//                throw new NullPointerException("Thumbnail resource not found: " + video.getThumbsrc());
//            }
//            Image thumbImage = new Image(thumbStream);
//            thumbnail.setImage(thumbImage);
//
//            InputStream profileStream = getClass().getResourceAsStream(video.getProfilesrc());
//            if (profileStream == null) {
//                logger.severe("Profile image resource not found: " + video.getProfilesrc());
//                throw new NullPointerException("Profile image resource not found: " + video.getProfilesrc());
//            }
//            Image profileImage = new Image(profileStream);
//            profile.setImage(profileImage);
//
//            title.setText(video.getName());
//            channelName.setText(video.getChannnel());
//            views.setText(video.getViews());
//            date.setText(video.getDate());
//        } catch (Exception e) {
//            logger.severe("Error setting data: " + e.getMessage());
//            e.printStackTrace();
//        }

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

//    @FXML
//    void handleClick(MouseEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("video.fxml"));
//        Parent root = loader.load();
//
//        // Access the controller of video.fxml
//        Videoplayer videoController = loader.getController();
//        videoController.setVideoData(video);
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

}
