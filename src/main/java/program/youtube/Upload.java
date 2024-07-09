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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Upload implements Initializable {
    @FXML
    private TextArea description;

    @FXML
    private ImageView thumbnail;

    @FXML
    private Button uploadbtn;

    @FXML
    private Label filePathLabel;

    private File selectedFile;

    private int num = 0;

    @FXML
    private MediaView mediaView;

    @FXML
    private TextField videoTitle;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String thumbnailPath;
    public static String title;
    public static String descriptionText;
    public static String theUser;

    public static String filepath;

    private static List<Video> uploadedVideos = new ArrayList<>();

    public static List<Video> getUploadedVideos() {
        return uploadedVideos;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filePathLabel.setVisible(false);
        setInitialValues();
    }

    private void setInitialValues() {
        title = "";
        descriptionText = "";
        thumbnailPath = null;
        theUser = Youtube.theUser;
    }

    @FXML
    void uploadbtn(ActionEvent event) {
        switch (num) {
            case 0:
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv")
                );
                selectedFile = fileChooser.showOpenDialog(new Stage());
                if (selectedFile != null) {
                    filePathLabel.setText(selectedFile.getAbsolutePath()); // Correctly display the file path
                    Media media = new Media(selectedFile.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    mediaPlayer.play();
                }
                num++;
                break;

            case 1:
                if (selectedFile != null) {
                    title = videoTitle.getText();
                    descriptionText = description.getText();
                    thumbnailPath = thumbnail.getImage() != null ? thumbnail.getImage().getUrl() : null;
                    theUser = Youtube.theUser;
                    filepath = selectedFile.getAbsolutePath().toString();

                    // Debugging output
                    System.out.println("Title: " + title);
                    System.out.println("Description: " + descriptionText);
                    System.out.println("File Path: " + selectedFile.getAbsolutePath());
                    System.out.println("Thumbnail Path: " + thumbnailPath);
                    System.out.println("User: " + theUser);

                    try {
                        Client client = new Client();
                        int id = client.get_user_id(theUser);
                        client.sending_video(title, selectedFile.getAbsolutePath(), theUser, id, descriptionText);
                        System.out.println("Uploading: " + selectedFile.getAbsolutePath());

                        // Load the profile page
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("myprofile.fxml"));
                        Parent root = loader.load();

                        MyProfileController profileController = loader.getController();
                        profileController.setThumbnailPath(thumbnailPath);

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Verify the file exists
                    if (!selectedFile.exists()) {
                        System.out.println("File does not exist: " + selectedFile.getAbsolutePath());
                        return;
                    }

                    // Add the video to the uploadedVideos list
//                    Video video = new Video();
//                    video.setName(title);
//                    video.setDescription(descriptionText);
//                    video.setVideosrc(selectedFile.getAbsolutePath());
//                    video.setThumbsrc(thumbnailPath);
//                    uploadedVideos.add(video);
//                    MyProfileController controller = new MyProfileController();
//                    controller.initialize();

                }
                break;
        }
    }

    @FXML
    void thumbnail(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image newImage = new Image(selectedFile.toURI().toString());
            thumbnail.setImage(newImage);
            thumbnailPath = selectedFile.toURI().toString();
        }
    }
}
