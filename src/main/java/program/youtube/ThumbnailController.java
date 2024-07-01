package program.youtube;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

//    private static final Logger logger = Logger.getLogger(ThumbnailController.class.getName());

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
}
