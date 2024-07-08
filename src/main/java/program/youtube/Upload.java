package program.youtube;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


    @FXML
    void uploadbtn(ActionEvent event) {
        switch (num){
            case 0:
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv")
                );
                selectedFile = fileChooser.showOpenDialog(new Stage());
                if (selectedFile != null) {
                    filePathLabel.setText(selectedFile.getAbsolutePath());
                }

                Media media = new Media(selectedFile.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();

                num ++;
                break;

            case 1:
                if (selectedFile != null) {
                    // Handle file upload logic here
                    // For example, you might want to pass the file path to the main controller
                    String title = videoTitle.getText();
                    String Description = description.getText();
                    String thumbnailPath = selectedFile.getAbsolutePath();
                    String TheUser = Youtube.theUser;
                    String path = filePathLabel.toString();

                    Client client = new Client();
                    int id = client.get_user_id(TheUser);
                    client.sending_video(title,path,TheUser,id,Description);

                    System.out.println("Uploading: " + selectedFile.getAbsolutePath());
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filePathLabel.setVisible(false);
    }
}
