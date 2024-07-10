package program.youtube;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Videoplayer implements Initializable {

    @FXML
    private Button addcommentbtn;

    @FXML
    private ImageView backforth;

    @FXML
    private Label channelname;

    @FXML
    private ImageView channelprofile;

    @FXML
    private TextArea commentArea;

    @FXML
    private ImageView commenter;

    @FXML
    private TextArea description;

    @FXML
    private ImageView dislikebtn;

    @FXML
    private ImageView likebtn;

    @FXML
    private Text likecount;


    @FXML
    private ImageView home;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox commentsVBox;

    @FXML
    private MediaView mediaView;

    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private ImageView playpause;

    @FXML
    private ImageView speaker;

    @FXML
    private Button subscribebtn;

    @FXML
    private Label subscribers;

    @FXML
    private Label subscribersnum;

    @FXML
    private Label timeduration;

    @FXML
    private VBox videoListVBox;

    private boolean isPlaying = false;

    @FXML
    private Label videotitle;

    @FXML
    void addcommentbtn(ActionEvent event) {
        String commentText = commentArea.getText();
        if (!commentText.isEmpty()) {
            HBox commentBox = new HBox(10); // Create an HBox with spacing of 10

            ImageView profileImage = new ImageView(new Image(getClass().getResourceAsStream(getUserProfileImage())));
            profileImage.setFitHeight(40);
            profileImage.setFitWidth(40);

            VBox userDetails = new VBox(5); // Create a VBox to hold user details
            Label usernameLabel = new Label(getUsername());
            Label commentLabel = new Label(commentText);

            userDetails.getChildren().addAll(usernameLabel, commentLabel);
            commentBox.getChildren().addAll(profileImage, userDetails);

            commentsVBox.getChildren().add(commentBox);
            commentArea.clear();

            // Scroll to the bottom to show the new comment
            scrollpane.layout();
            scrollpane.setVvalue(1.0);
        }
    }

    private String getUserProfileImage() {
        return "/images/avatar1.jpg";
    }

    private String getUsername() {
        return "farshadsilent";
    }

    @FXML
    void backforth(MouseEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(10)));
    }

    @FXML
    void dislikebtn(MouseEvent event) {

    }

    @FXML
    void likebtn(MouseEvent event) {

    }

    @FXML
    void likecount(MouseEvent event) {

    }

    public void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    @FXML
    void home(MouseEvent event) throws IOException {
        stopVideo();
        Parent root = FXMLLoader.load(getClass().getResource("theYoutube.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        mediaPlayer.setMute(!mediaPlayer.isMute());
        speaker.setImage(new Image(getClass().getResourceAsStream(mediaPlayer.isMute() ? "/images/mute.png" : "/images/speaker.png")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initial setup can be done here if needed
        // For demo purposes, adding some videos to sidebar
        addVideoToSidebar("Video Title 1", "Channel 1", "2024-07-03", "1K views", "/images/thumb3.jpg");
        addVideoToSidebar("Video Title 2", "Channel 2", "2024-06-20", "500 views", "/images/thumb2.jpg");
        addVideoToSidebar("Video Title 3", "Channel 3", "2024-05-15", "10K views", "/images/thumb5.jpg");
        addVideoToSidebar("Video Title 1", "Channel 1", "2024-07-03", "1K views", "/images/thumb3.jpg");
        addVideoToSidebar("Video Title 2", "Channel 2", "2024-06-20", "500 views", "/images/thumb2.jpg");
        addVideoToSidebar("Video Title 3", "Channel 3", "2024-05-15", "10K views", "/images/thumb5.jpg");
        addVideoToSidebar("Video Title 1", "Channel 1", "2024-07-03", "1K views", "/images/thumb3.jpg");
        addVideoToSidebar("Video Title 2", "Channel 2", "2024-06-20", "500 views", "/images/thumb2.jpg");
        addVideoToSidebar("Video Title 3", "Channel 3", "2024-05-15", "10K views", "/images/thumb5.jpg");
        addVideoToSidebar("Video Title 1", "Channel 1", "2024-07-03", "1K views", "/images/thumb3.jpg");
        addVideoToSidebar("Video Title 2", "Channel 2", "2024-06-20", "500 views", "/images/thumb2.jpg");
        addVideoToSidebar("Video Title 3", "Channel 3", "2024-05-15", "10K views", "/images/thumb5.jpg");
    }

    private void addVideoToSidebar(String title, String channel, String date, String views, String thumbnailPath) {
        HBox videoItem = new HBox(10); // Create an HBox with spacing of 10

        ImageView thumbnail = new ImageView(new Image(getClass().getResourceAsStream(thumbnailPath)));
        thumbnail.setFitHeight(80);
        thumbnail.setFitWidth(120);

        VBox videoDetails = new VBox(5); // Create a VBox to hold video details
        Label titleLabel = new Label(title);
        Label channelLabel = new Label(channel);
        Label dateLabel = new Label(date);
        Label viewsLabel = new Label(views);

        videoDetails.getChildren().addAll(titleLabel, channelLabel, dateLabel, viewsLabel);
        videoItem.getChildren().addAll(thumbnail, videoDetails);

        videoListVBox.getChildren().add(videoItem);
    }

    public void setVideoData(Video video) {
        System.out.println(video.getVideosrc());

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

        mediaPlayer.setOnReady(() -> {
            Duration total = mediaPlayer.getMedia().getDuration();
        });
    }

    @FXML
    void subscribebtn(ActionEvent event) {

    }
}
