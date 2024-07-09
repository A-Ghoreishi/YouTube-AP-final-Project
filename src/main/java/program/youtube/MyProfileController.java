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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static program.youtube.Upload.descriptionText;

public class MyProfileController {

    @FXML
    private Button createbtn;

    @FXML
    private HBox gaminhbox;

    @FXML
    private HBox historyhbox;

    @FXML
    private HBox homehbox;

    @FXML
    private HBox likedvideoshbox;

    @FXML
    private TextField lname;

    @FXML
    private ImageView menu;

    @FXML
    private VBox middlepage;

    @FXML
    private TextField name;

    @FXML
    private ImageView notification;

    @FXML
    private HBox playlisthbox;

    @FXML
    private HBox premiumhbox;

    @FXML
    private ImageView profile;

    @FXML
    private ImageView profilepic;

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
    private Label username;

    @FXML
    private GridPane videoGrid;

    @FXML
    private HBox watchlaterhbox;

    @FXML
    private HBox yourchannelhbox;

    @FXML
    private HBox yourvideoshbox;

    private String thumbnailPath;

    List<Video> videos = Upload.getUploadedVideos(); // Assuming Upload is a class to fetch uploaded videos

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @FXML
    void initialize() {
        int columns = 3;
        int rowIndex = 0;
        int columnIndex = 0;

        Video video = new Video();
        video.setName(Upload.title);
        video.setDescription(Upload.descriptionText);
        video.setVideosrc(Upload.filepath);
        video.setThumbsrc(Upload.thumbnailPath);
        videos.add(video);

        for (Video videO : videos) {
            System.out.println("Video thumbnail path: " + video.getThumbsrc());

            VBox videoBox = new VBox();
            ImageView thumbnailView = new ImageView();

            try {
                Image thumbnailImage = new Image(video.getThumbsrc());
                thumbnailView.setImage(thumbnailImage);
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
//                continue; // Skip this video if the image can't be loaded
            }

            Label titleLabel = new Label(video.getName());
            videoBox.getChildren().addAll(thumbnailView, titleLabel);

            // Add click event handler to open video player
            videoBox.setOnMouseClicked(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("videoplayer.fxml"));
                    Parent root = loader.load();
                    Videoplayer videoplayerController = loader.getController();
                    videoplayerController.setVideoData(videO); // Pass the clicked video data to Videoplayer controller

                    Stage stage = new Stage(); // Create a new stage for the video player
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            videoGrid.add(videoBox, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex >= columns) {
                columnIndex = 0;
                rowIndex++;
            }

            GridPane.setMargin(videoBox, new Insets(10));
        }
    }



//    private void clearVideoGrid() {
//        videoGrid.getChildren().clear();
//    }

    private void displayVideosInGrid(List<Video> videos) {
//        int columns = 3;
//        int rowIndex = 0;
//        int columnIndex = 0;
//
//        for (Video video : videos) {
//            System.out.println("Video thumbnail path: " + video.getThumbsrc());
//            VBox videoBox = new VBox();
//            ImageView thumbnailView = new ImageView();
//
//            try {
//                Image thumbnailImage = new Image(video.getThumbsrc());
//                thumbnailView.setImage(thumbnailImage);
//            } catch (Exception e) {
//                System.out.println("Error loading image: " + e.getMessage());
//                continue; // Skip this video if the image can't be loaded
//            }
//
//            Label titleLabel = new Label(video.getName());
//            videoBox.getChildren().addAll(thumbnailView, titleLabel);
//
//            // Add click event handler to open video player
//            videoBox.setOnMouseClicked(event -> {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("videoplayer.fxml"));
//                    Parent root = loader.load();
//                    Videoplayer videoplayerController = loader.getController();
//                    videoplayerController.setVideoData(video); // Pass the clicked video data to Videoplayer controller
//
//                    Stage stage = new Stage(); // Create a new stage for the video player
//                    stage.setScene(new Scene(root));
//                    stage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//
//            videoGrid.add(videoBox, columnIndex, rowIndex);
//            columnIndex++;
//            if (columnIndex >= columns) {
//                columnIndex = 0;
//                rowIndex++;
//            }
//
//            GridPane.setMargin(videoBox, new Insets(10));
//        }
    }

    @FXML
    void createbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gaminhbox(MouseEvent event) {}

    @FXML
    void historyhbox(MouseEvent event) {}

    @FXML
    void homehbox(MouseEvent event) {
        try {
            // Set UserInfo
            UserInfo userInfo = new UserInfo();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("theYoutube.fxml"));
            Parent root = loader.load();
            TheYouTube controller = loader.getController();
            controller.setUserInfo(userInfo);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void likedvideoshbox(MouseEvent event) {}

    @FXML
    void lname(MouseEvent event) {
        String Name = lname.getText();
        lname.setText(Name);
    }

    @FXML
    void menu(MouseEvent event) {}

    @FXML
    void middlepage(MouseEvent event) {}

    @FXML
    void name(MouseEvent event) {
        String Name = name.getText();
        name.setText(Name);
    }

    @FXML
    void notification(MouseEvent event) {}

    @FXML
    void playlisthbox(MouseEvent event) {}

    @FXML
    void premiumhbox(MouseEvent event) {}

    @FXML
    void profile(MouseEvent event) {}

    @FXML
    void profilepic(MouseEvent event) {
        String profilePicPath;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image newImage = new Image(selectedFile.toURI().toString());
            profile.setImage(newImage);  // Assuming `profile` is the ImageView for the profile picture
            profilepic.setImage(newImage);
            profilePicPath = selectedFile.getAbsolutePath(); // Save the file path

            Client client = new Client();
            int userID = client.get_user_id(Youtube.theUser);
            client.send_profile_picture(profilePicPath, userID);
        }
    }

    @FXML
    void reporthbox(MouseEvent event) {}

    @FXML
    void settinghbox(MouseEvent event) {}

    @FXML
    void subscriptionhbox(MouseEvent event) {}

    @FXML
    void trendinghbox(MouseEvent event) {}

    @FXML
    void uploadvideohbox(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("upload.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void videoGrid(MouseEvent event) {}

    @FXML
    void watchlaterhbox(MouseEvent event) {}

    @FXML
    void yourchannelhbox(MouseEvent event) {}

    @FXML
    void yourvideoshbox(MouseEvent event) {}

}
