package program.youtube;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Youtube {

    private String selectedGender;
    private String selectedMonth;

    @FXML
    private Text basicInfo;

    @FXML
    private TextField biobar;

    @FXML
    private Button Subscriptionbtn;

    @FXML
    private Text birth;

    @FXML
    private Text createAccount;

    @FXML
    private Button createAccbtn;

    @FXML
    private TextField day;

    @FXML
    private TextField emailbar;

    @FXML
    private Text emailyoutube;

    @FXML
    private Text existingemail;

    @FXML
    private TextField firstname;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private ImageView home;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField lastname;

    @FXML
    private ImageView menu;

    @FXML
    private ComboBox<String> month;

    @FXML
    private Button moreBtn;

    @FXML
    private Text mustBirth;

    @FXML
    private Text mustEmail;

    @FXML
    private Text mustName;

    @FXML
    private Text mustPass;

    @FXML
    private Button nextBtn;

    @FXML
    private TextField passBar;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchbtn;

    @FXML
    private ImageView shorts;

    @FXML
    private Button shortsbtn;

    @FXML
    private Text signin;

    @FXML
    private Button signupBtn;

    @FXML
    private ImageView subscription;

    @FXML
    private ImageView thumbnailImage;

    @FXML
    private TextField username;

    @FXML
    private Text welcomeText;

    @FXML
    private TextField year;

    @FXML
    private ImageView youtube;

    @FXML
    private ImageView youtube2;

    @FXML
    private Button youtubebtn;

    private  int clicked = 0;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String theUser;
    ObservableList<String> options = FXCollections.observableArrayList(
            "Male",
            "Female",
            "Custom",
            "Rather Not Say"
    );

    ObservableList<String> months = FXCollections.observableArrayList(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    );



    @FXML
    public void initialize(){
        gender.setItems(options);
        month.setItems(months);

        // Add listeners to the ComboBoxes
        gender.setOnAction(this::handleGenderSelection);
        month.setOnAction(this::handleMonthSelection);

        homePage(true);
        signIn(false);
        signUp1(false);
        signUp2(false);
        signUp3(false);
        mustEmail.setVisible(false);
        mustName.setVisible(false);
        mustPass.setVisible(false);
        mustBirth.setVisible(false);
        thumbnailImage.setVisible(false);
    }

    private void handleGenderSelection(ActionEvent event) {
        selectedGender = gender.getValue();
        System.out.println("Selected Gender: " + selectedGender);
    }

    private void handleMonthSelection(ActionEvent event) {
        selectedMonth = month.getValue();
        System.out.println("Selected Month: " + selectedMonth);
    }

    public void signIn(boolean isVisible){
        signin.setVisible(isVisible);
        welcomeText.setVisible(isVisible);
        username.setVisible(isVisible);
        passBar.setVisible(isVisible);
        nextBtn.setVisible(isVisible);
        createAccbtn.setVisible(isVisible);
        youtube2.setVisible(isVisible);

    }
    public void homePage(boolean isVisible){
        menu.setVisible(isVisible);
        home.setVisible(isVisible);
        homeBtn.setVisible(isVisible);
        shorts.setVisible(isVisible);
        shortsbtn.setVisible(isVisible);
        subscription.setVisible(isVisible);
        Subscriptionbtn.setVisible(isVisible);
        youtube.setVisible(isVisible);
        youtubebtn.setVisible(isVisible);
        searchBar.setVisible(isVisible);
        searchbtn.setVisible(isVisible);
        moreBtn.setVisible(isVisible);
        signupBtn.setVisible(isVisible);

    }

    public void signUp1(boolean isVisible){
        firstname.setVisible(isVisible);
        lastname.setVisible(isVisible);
        createAccount.setVisible(isVisible);
        nextBtn.setVisible(isVisible);
        youtube2.setVisible(isVisible);
        username.setVisible(isVisible);
    }

    public void signUp2(boolean isVisible){
        birth.setVisible(isVisible);
        month.setVisible(isVisible);
        day.setVisible(isVisible);
        year.setVisible(isVisible);
        gender.setVisible(isVisible);
        nextBtn.setVisible(isVisible);
        youtube2.setVisible(isVisible);
        basicInfo.setVisible(isVisible);
    }

    public void signUp3(boolean isVisible){
        emailbar.setVisible(isVisible);
        passBar.setVisible(isVisible);
        nextBtn.setVisible(isVisible);
        youtube2.setVisible(isVisible);
        existingemail.setVisible(isVisible);
        emailyoutube.setVisible(isVisible);
        biobar.setVisible(isVisible);
    }

    @FXML
    void basicInfo(MouseEvent event) {

    }

    @FXML
    void biobar(ActionEvent event) {

    }

    @FXML
    void birth(MouseEvent event) {

    }

    @FXML
    void createAccbtn(ActionEvent event) {
        signIn(false);
        signUp1(true);
        clicked = 1;
    }

    @FXML
    void createAccount(MouseEvent event) {

    }

    @FXML
    void day(ActionEvent event) {

    }

    @FXML
    void emailbar(ActionEvent event) {

    }

    @FXML
    void emailyoutube(MouseEvent event) {

    }

    @FXML
    void existingemail(MouseEvent event) {

    }

    @FXML
    void firstname(ActionEvent event) {

    }

    @FXML
    void gender(ActionEvent event) {

    }

    @FXML
    void home(MouseEvent event) {

    }

    @FXML
    void homeBtn(ActionEvent event) {
        homePage(true);
    }

    @FXML
    void im(ActionEvent event) {

    }

    @FXML
    void lastname(ActionEvent event) {

    }

    @FXML
    void menu(MouseEvent event) {

    }

    @FXML
    void month(ActionEvent event) {

    }

    @FXML
    void moreBtn(ActionEvent event) {

    }

    @FXML
    void mustBirth(MouseEvent event) {

    }

    @FXML
    void mustEmail(MouseEvent event) {

    }

    @FXML
    void mustName(MouseEvent event) {

    }

    @FXML
    void mustPass(MouseEvent event) {

    }

    @FXML
    void nextBtn(ActionEvent event) {

        theUser = username.getText();

        if (clicked == 0) {
            if (username.getText().isEmpty() || passBar.getText().isEmpty()) {
                if (username.getText().isEmpty()) {
                    mustEmail.setVisible(true);
                }

                if (passBar.getText().isEmpty()) {
                    mustPass.setVisible(true);
                }
            } else {
                String Username = username.getText();
                String Password = passBar.getText();
                Client client = new Client();


                if (client.login(Username, Password)) {
                    try {
                        // Set UserInfo
                        UserInfo userInfo = new UserInfo();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("theYoutube.fxml"));
                        Parent root = loader.load();
                        TheYouTube controller = loader.getController();
                        controller.setUserInfo(userInfo);

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("invalid");
                }
            }
        }

        if (clicked == 1) {
            if (firstname.getText().isEmpty() || username.getText().isEmpty()) {
                if (firstname.getText().isEmpty()) {
                    mustName.setVisible(true);
                }
            } else {
                String name = firstname.getText();
                String lname = lastname.getText();
                Client client = new Client();
                client.sending_fname_lname_user_name(name,lname,theUser);
//                if (Database.inserting_name_username_lname(name, lname, theUser)) {
//
//                }
                signUp1(false);
                signIn(false);
                signUp2(true);
                mustName.setVisible(false);
                clicked++;
            }
        } else if (clicked == 2) {
            if (gender.getSelectionModel().isEmpty() || month.getSelectionModel().isEmpty() || day.getText().isEmpty() || year.getText().isEmpty()) {
                mustBirth.setVisible(true);
            } else {
                int Day = Integer.parseInt(day.getText());
                int Year = Integer.parseInt(year.getText());
                String Month = selectedMonth;
                String Gender = selectedGender;

                Client client = new Client();
                client.sending_birth_dates(theUser,Year,Month,Day,Gender);

                signUp2(false);
                signUp3(true);
                mustBirth.setVisible(false);
                clicked++;
            }
        } else if (clicked == 3) {
            if (emailbar.getText().isEmpty() || passBar.getText().isEmpty()) {
                if (emailbar.getText().isEmpty()) {
                    mustEmail.setVisible(true);
                }

                if (passBar.getText().isEmpty()) {
                    mustPass.setVisible(true);
                }
            } else {
                // getname of UserInfo class and then again home page but this time with videos
                String Email = emailbar.getText();
                String PassWord = passBar.getText();
                String Bio = biobar.getText();
                Client client = new Client();
                client.send_email_password_bio(theUser,Email,PassWord,Bio);

                try {
                    // Set UserInfo
                    UserInfo userInfo = new UserInfo();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("theYoutube.fxml"));
                    Parent root = loader.load();
                    TheYouTube controller = loader.getController();
                    controller.setUserInfo(userInfo);

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    void passBar(ActionEvent event) {

    }

    @FXML
    void searchBar(ActionEvent event) {

    }

    @FXML
    void searchbtn(ActionEvent event) {
        //look for info in database
    }

    @FXML
    void shorts(MouseEvent event) {

    }

    @FXML
    void signin(MouseEvent event) {

    }

    @FXML
    void signupBtn(ActionEvent event) {
        homePage(false);
        signIn(true);
    }

    @FXML
    void subscription(MouseEvent event) {

    }

    @FXML
    void subscriptionbtn(ActionEvent event) {

    }

    @FXML
    void thumbnailImage(MouseEvent event) {

    }

    @FXML
    void username(ActionEvent event) {

    }

    @FXML
    void welcomeText(MouseEvent event) {

    }

    @FXML
    void year(ActionEvent event) {

    }

    @FXML
    void youtube(MouseEvent event) {

    }

    @FXML
    void youtube2(MouseEvent event) {

    }

    @FXML
    void youtubebtn(ActionEvent event) {

    }

}
