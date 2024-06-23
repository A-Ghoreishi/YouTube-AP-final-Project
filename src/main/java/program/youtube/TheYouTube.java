package program.youtube;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TheYouTube implements Initializable {

    @FXML
    private GridPane videoGrid;
    private List<Video> videos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videos = new ArrayList<>(videos());

        int columns = 0;
        int rows = 1;

        try{
            for(int i = 0; i < videos.size(); i ++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("thumbnail.fxml"));

                VBox box = fxmlLoader.load();
                ThumbnailController thumbnailController = fxmlLoader.getController();
                thumbnailController.setData(videos.get(i));

                if(columns == 3){
                    columns = 0;
                    ++rows;
                }

                videoGrid.add(box,columns++,rows);
                GridPane.setMargin(box,new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Video> videos(){
        List<Video> ls = new ArrayList<>();

        for(int i = 0; i< 10; i ++){
            Video video = new Video();
            video.setThumbsrc("/image/thumbnail1.jpg");
            video.setProfilesrc("/image/avatar2.png");
            video.setName("Top 5 professional thumbnails");
            video.setChannnel("MargaretYt");
            video.setViews("123K views");
            video.setDate("1 year ago");
            ls.add(video);

        }
        return ls;
    }
}
