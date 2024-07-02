package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

public class Videoplayer implements Initializable {

    @FXML
    private ImageView backforth;

    @FXML
    private MediaView mediaView;

    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private ImageView playpause;

    @FXML
    private ImageView speaker;

    @FXML
    private Label timeduration;

    private boolean isPlaying = false;

    @FXML
    void backforth(MouseEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(10)));
    }

    @FXML
    void playpause(MouseEvent event) {
        if (isPlaying) {
            mediaPlayer.pause();
            playpause.setImage(new Image(getClass().getResourceAsStream("/images/play.png")));
        } else {
            mediaPlayer.play();
            playpause.setImage(new Image(getClass().getResourceAsStream("/images/pause.png")));
        }
        isPlaying = !isPlaying;
    }

    @FXML
    void speaker(MouseEvent event) {
        // Toggle mute
        mediaPlayer.setMute(!mediaPlayer.isMute());
        speaker.setImage(new Image(getClass().getResourceAsStream(mediaPlayer.isMute() ? "/images/mute.png" : "/images/speaker.png")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initial setup can be done here if needed
    }

    public void setVideoData(Video video) {
        String videoPath = getClass().getResource(video.getVideosrc()).toString();
        media = new Media(videoPath);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // Optional: Auto-play the video
        mediaPlayer.play();
        isPlaying = true;
        playpause.setImage(new Image(getClass().getResourceAsStream("/images/pause.png")));

        // Update the time label
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            timeduration.setText(String.format("%02d:%02d",
                    (int) newValue.toMinutes(),
                    (int) newValue.toSeconds() % 60));
        });

        // Set the maximum value of the progress bar to the total duration of the media
        mediaPlayer.setOnReady(() -> {
            Duration total = mediaPlayer.getMedia().getDuration();
            // progressBar.setMax(total.toSeconds()); // If using a progress bar
        });
    }
}
