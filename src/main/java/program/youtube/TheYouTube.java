package program.youtube;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private UserInfo userInfo;

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
    private Button premium;

    @FXML
    private ImageView profile;

    @FXML
    private HBox reporthbox;

    @FXML
    private HBox settinghbox;

    @FXML
    private HBox subscriptionhbox;

    @FXML
    private TextField searchbar;

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
    private ImageView searchbtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // playlisttext.setVisible(false);

        videos = new ArrayList<>();

        Video video1 = new Video();
        video1.setThumbsrc("/images/thumb1.jpg");
        video1.setProfilesrc("/images/pro1.jpg");
        video1.setName("Top 10 people");
        video1.setChannel("MargaretYt");
        video1.setViews("123K views");
        video1.setDate("1 year ago");
        video1.setVideosrc("/images/video1.mp4");
        videos.add(video1);

        Video video2 = new Video();
        video2.setThumbsrc("/images/thumb2.jpg");
        video2.setProfilesrc("/images/pro2.jpg");
        video2.setName("Top 5 professional thumbnails");
        video2.setChannel("Mart");
        video2.setViews("321M views");
        video2.setDate("1 month ago");
        video2.setVideosrc("/images/file.mp4");
        videos.add(video2);

        int columns = 0;
        int rows = 1;

        try {
            for (int i = 0; i < videos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("thumbnail.fxml"));

                VBox box = fxmlLoader.load();
                ThumbnailController thumbnailController = fxmlLoader.getController();
                thumbnailController.setData(videos.get(i));

                // Set an ID for each VBox for identification
                box.setId("thumbnail-" + i);

                final int index = i; // This makes `index` effectively final
                box.setOnMouseClicked(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("videoplayer.fxml"));
                        Parent root = loader.load();
                        Videoplayer videoplayerController = loader.getController();
                        videoplayerController.setVideoData(videos.get(index));

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                if (columns == 3) {
                    columns = 0;
                    ++rows;
                }

                videoGrid.add(box, columns++, rows);
                GridPane.setMargin(box, new Insets(10));
            }
//            Client client = new Client();
//            int userID = client.get_user_id(Youtube.theUser);
//            String propath = client.get_profile_picture(userID);
//            System.out.println(propath);
//            Image image = new Image(propath);
//            profile.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void premium(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("musicpremium.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void premiumhbox(MouseEvent event) {
    }

    @FXML
    void profile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("myprofile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void reporthbox(MouseEvent event) {
    }

    @FXML
    void searchbar(MouseEvent event) {

    }
    @FXML
    void searchbtn(MouseEvent event) throws IOException {
        String search = searchbar.getText();
        Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Client client = new Client();
        client.search_video(search);

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
    void uploadvideohbox(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
}
