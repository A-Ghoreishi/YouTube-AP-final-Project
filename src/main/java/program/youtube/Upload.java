package program.youtube;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button uploadbtn;

    @FXML
    private Label filePathLabel;

    private File selectedFile;

    private int num = 0;

    @FXML
    private MediaView mediaView;

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
                    System.out.println("Uploading: " + selectedFile.getAbsolutePath());
                }
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filePathLabel.setVisible(false);
    }
}
