package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Videoplayer2 implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView play;

    @FXML
    private Slider progressBar;

    private boolean isPlaying = false;
    private MediaPlayer mediaPlayer;

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Set initial play button image
//        Image playImage = new Image(getClass().getResourceAsStream("/images/play.png"));
//        play.setImage(playImage);
//
//        play.setOnMouseClicked(event -> {
//            if (isPlaying) {
//                mediaPlayer.pause();
//                play.setImage(playImage);
//            } else {
//                mediaPlayer.play();
//                play.setImage(new Image(getClass().getResourceAsStream("/images/pause.png")));
//            }
//            isPlaying = !isPlaying;
//        });
//
//        progressBar.setOnMousePressed(event -> {
//            mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
//        });
//
//        progressBar.setOnMouseDragged(event -> {
//            mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
//        });
  }
//
//    public void setVideoData(Video video) {
//        String videoPath = new File(video.getVideosrc()).toURI().toString();
//        Media media = new Media(videoPath);
//        if (mediaPlayer != null) {
//            mediaPlayer.stop();
//        }
//        mediaPlayer = new MediaPlayer(media);
//        mediaView.setMediaPlayer(mediaPlayer);
//
//        // Optional: Auto-play the video
//        mediaPlayer.play();
//
//        // Bind the progress bar to the media player's current time
//        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
//            progressBar.setValue(newValue.toSeconds());
//        });
//
//        // Set the maximum value of the progress bar to the total duration of the media
//        mediaPlayer.setOnReady(() -> {
//            Duration total = mediaPlayer.getMedia().getDuration();
//            progressBar.setMax(total.toSeconds());
//        });
//    }
}
