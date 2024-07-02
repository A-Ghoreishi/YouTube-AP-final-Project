package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TheYouTube implements Initializable {

    @FXML
    private GridPane videoGrid;
    private List<Video> videos;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox middlepage;

    @FXML
    private Label playlisttext;

    @FXML
    private HBox gaminhbox;

    @FXML
    private HBox historyhbox;

    @FXML
    private HBox homehbox;

    @FXML
    private HBox likedvideoshbox;

    @FXML
    private ImageView menu;

    @FXML
    private ImageView notification;

    @FXML
    private HBox playlisthbox;

    @FXML
    private HBox premiumhbox;

    @FXML
    private ImageView profile;

    @FXML
    private HBox reporthbox;

    @FXML
    private HBox settinghbox;

    @FXML
    private HBox subscriptionhbox;

    @FXML
    private HBox trendinghbox;

    @FXML
    private HBox uploadvideohbox;

    @FXML
    private HBox watchlaterhbox;

    @FXML
    private HBox yourchannelhbox;

    @FXML
    private HBox yourvideoshbox;

    @FXML
    public void initialize(){
        playlisttext.setVisible(false);
    }
    @FXML
    void gaminhbox(MouseEvent event) {

    }

    @FXML
    void historyhbox(MouseEvent event) {

    }

    @FXML
    void homehbox(MouseEvent event) {

    }

    @FXML
    void likedvideoshbox(MouseEvent event) {

    }

    @FXML
    void menu(MouseEvent event) {

    }

    @FXML
    void middlepage(MouseEvent event) {

    }

    @FXML
    void notification(MouseEvent event) {

    }

    @FXML
    void playlisthbox(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playlist.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void premiumhbox(MouseEvent event) {

    }

    @FXML
    void profile(MouseEvent event) {

    }

    @FXML
    void reporthbox(MouseEvent event) {

    }

    @FXML
    void settinghbox(MouseEvent event) {

    }

    @FXML
    void subscriptionhbox(MouseEvent event) {

    }

    @FXML
    void trendinghbox(MouseEvent event) {

    }

    @FXML
    void uploadvideohbox(MouseEvent event) {
        try {
            System.out.println("Trying to load FXML...");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("upload.fxml"));
            Parent root = fxmlLoader.load();
            System.out.println("FXML loaded successfully.");

            Stage stage = new Stage();
            stage.setTitle("Upload your video");
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Stage shown.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void videoGrid(MouseEvent event) {

    }

    @FXML
    void watchlaterhbox(MouseEvent event) {

    }

    @FXML
    void yourchannelhbox(MouseEvent event) {

    }

    @FXML
    void yourvideoshbox(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videos = new ArrayList<>();

        Video video = new Video();
        video.setThumbsrc("/images/thumb1.jpg");
        video.setProfilesrc("/images/pro1.jpg");
        video.setName("Top 5 professional videos");
        video.setChannel("MargaretYt");
        video.setViews("123K views");
        video.setDate("1 year ago");
        videos.add(video);

        video = new Video();
        video.setThumbsrc("/images/thumb2.jpg");
        video.setProfilesrc("/images/pro2.jpg");
        video.setName("Top 5 professional thumbnails");
        video.setChannel("Mart");
        video.setViews("321M views");
        video.setDate("1 month ago");
        videos.add(video);

        int columns = 0;
        int rows = 1;

        try {
            for (int i = 0; i < videos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("thumbnail.fxml"));

                VBox box = fxmlLoader.load();
                ThumbnailController thumbnailController = fxmlLoader.getController();
                thumbnailController.setData(videos.get(i));

                if (columns == 3) {
                    columns = 0;
                    ++rows;
                }

                videoGrid.add(box, columns++, rows);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    private List<Video> videos(){
//        List<Video> ls = new ArrayList<>();
//
//
//        Video video = new Video();
//        video.setThumbsrc("/images/thumb1.jpg");
//        video.setProfilesrc("/images/pro1.jpg");
//        video.setName("Top 5 professional videos");
//        video.setChannel("MargaretYt");
//        video.setViews("123K views");
//        video.setDate("1 year ago");
//        ls.add(video);
//
//        video= new Video();
//        video.setThumbsrc("/images/thumb2.jpg");
//        video.setProfilesrc("/images/pro2.jpg");
//        video.setName("Top 5 professional thumbnails");
//        video.setChannel("Mart");
//        video.setViews("321M views");
//        video.setDate("1 month ago");
//        ls.add(video);
//
//        return ls;
//    }
}
