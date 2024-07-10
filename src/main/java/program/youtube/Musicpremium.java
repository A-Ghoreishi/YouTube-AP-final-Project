package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Musicpremium {

    @FXML
    private HBox breakofdawn;

    @FXML
    private HBox coraline;

    @FXML
    private ImageView darklight;

    @FXML
    private HBox daylight;

    @FXML
    private HBox eyesclosed;

    @FXML
    private HBox gaminhbox;

    @FXML
    private HBox historyhbox;

    @FXML
    private HBox homehbox;

    @FXML
    private HBox hotel;

    @FXML
    private HBox likedvideoshbox;

    @FXML
    private ImageView menu;

    @FXML
    private VBox middlepage;

    @FXML
    private ImageView notification;

    @FXML
    private HBox oceandrive;

    @FXML
    private BorderPane parent;

    @FXML
    private VBox parentLeft;

    @FXML
    private HBox parentUp;

    @FXML
    private HBox perfect;

    @FXML
    private HBox playlisthbox;

    @FXML
    private HBox premiumhbox;

    @FXML
    private ImageView profile;

    @FXML
    private HBox reporthbox;

    @FXML
    private HBox savedyourtears;

    @FXML
    private TextField searchbar;

    @FXML
    private ImageView searchbtn;

    @FXML
    private HBox settinghbox;

    @FXML
    private HBox skyfall;

    @FXML
    private HBox subscriptionhbox;

    @FXML
    private HBox thegreatest;

    @FXML
    private HBox thenights;

    @FXML
    private HBox trendinghbox;

    @FXML
    private HBox up;

    @FXML
    private HBox uploadvideohbox;

    @FXML
    private HBox venom;

    @FXML
    private HBox watchlaterhbox;

    @FXML
    private HBox yourchannelhbox;

    @FXML
    private HBox yourvideoshbox;

    private MediaPlayer mediaPlayer;

    // Map to store HBox and their corresponding audio file URLs
    private Map<HBox, String> audioMap = new HashMap<>();

    @FXML
    public void initialize() {
        // Populate the map with HBox and their corresponding audio file URLs
        audioMap.put(breakofdawn, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/45963001859256878012.mp3");
        audioMap.put(coraline, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/45888475844434725535.mp3");
        audioMap.put(daylight, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/45945171250987929824.mp3");
        audioMap.put(eyesclosed, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/eyesclosed.mp3");
        audioMap.put(hotel, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/EaglesHotelCalifornia.mp3");
        audioMap.put(oceandrive, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/46043913360871788213.mp3");
        audioMap.put(perfect, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/perfect.mp3");
        audioMap.put(savedyourtears, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/saveyourtears.mp3");
        audioMap.put(skyfall, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/skyfall.mp3");
        audioMap.put(thegreatest, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/sia.mp3");
        audioMap.put(thenights, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/AviciiTheNights.mp3");
        audioMap.put(up,"C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/up.mp3");
        audioMap.put(venom, "C:/Users/Ailin Ghoreishi/Music/Java/FINAL_AP_PROJECT_YOUTUBE/src/main/resources/images/venom.mp3");

        // Add click event listeners to each HBox
        addClickEvent(breakofdawn);
        addClickEvent(coraline);
        addClickEvent(daylight);
        addClickEvent(eyesclosed);
        addClickEvent(hotel);
        addClickEvent(oceandrive);
        addClickEvent(perfect);
        addClickEvent(savedyourtears);
        addClickEvent(skyfall);
        addClickEvent(thegreatest);
        addClickEvent(thenights);
        addClickEvent(venom);
    }

    private void addClickEvent(HBox hbox) {
        hbox.setOnMouseClicked(event -> playAudio(hbox));
    }

    private void playAudio(HBox hbox) {
        String audioPath = audioMap.get(hbox);
        if (audioPath != null) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            File audioFile = new File(audioPath);
            Media media = new Media(audioFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }

    @FXML
    void breakofdawn(MouseEvent event) {}

    @FXML
    void coraline(MouseEvent event) {}

    @FXML
    void darklight(MouseEvent event) {}

    @FXML
    void daylight(MouseEvent event) {}

    @FXML
    void eyesclosed(MouseEvent event) {}

    @FXML
    void gaminhbox(MouseEvent event) {}

    @FXML
    void historyhbox(MouseEvent event) {}

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    @FXML
    void homehbox(MouseEvent event) throws IOException {
        stopMusic();
        Parent root = FXMLLoader.load(getClass().getResource("theYoutube.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hotel(MouseEvent event) {}

    @FXML
    void likedvideoshbox(MouseEvent event) {}

    @FXML
    void menu(MouseEvent event) {}

    @FXML
    void middlepage(MouseEvent event) {}

    @FXML
    void notification(MouseEvent event) {}

    @FXML
    void oceandrive(MouseEvent event) {}

    @FXML
    void perfect(MouseEvent event) {}

    @FXML
    void playlisthbox(MouseEvent event) {}

    @FXML
    void premiumhbox(MouseEvent event) {}

    @FXML
    void profile(MouseEvent event) {}

    @FXML
    void reporthbox(MouseEvent event) {}

    @FXML
    void savedyourtears(MouseEvent event) {}

    @FXML
    void searchbar(MouseEvent event) {}

    @FXML
    void searchbtn(MouseEvent event) {}

    @FXML
    void settinghbox(MouseEvent event) {}

    @FXML
    void skyfall(MouseEvent event) {}

    @FXML
    void subscriptionhbox(MouseEvent event) {}

    @FXML
    void thegreatest(MouseEvent event) {}

    @FXML
    void thenights(MouseEvent event) {}

    @FXML
    void trendinghbox(MouseEvent event) {}

    @FXML
    void up(MouseEvent event) {}

    @FXML
    void uploadvideohbox(MouseEvent event) {}

    @FXML
    void venom(MouseEvent event) {}

    @FXML
    void watchlaterhbox(MouseEvent event) {}

    @FXML
    void yourchannelhbox(MouseEvent event) {}

    @FXML
    void yourvideoshbox(MouseEvent event) {}
}
