package program.youtube;

import java.sql.*;
import java.sql.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class database {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "1234";



    public static void inserting_birth_date_and_gender(int year,String birth_month,int day,String user_name,String gender){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE user_info\n" +
                    "SET birth_year = ?,\n" +
                    "    birth_month = ?,\n" +
                    "    birth_day = ?,\n" +
                    "    gender = ? \n"+
                    "WHERE user_name = ?;\n";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1,year);
                preparedStatement.setString(2,birth_month);
                preparedStatement.setInt(3,day);
                preparedStatement.setString(4,gender);
                preparedStatement.setString(5,user_name);

                // Set the array to the prepared statement
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUsernameExists(String username) {
        boolean usernameExists = false;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement st = connection.prepareStatement("SELECT * FROM user_info WHERE user_name = ?");
            st.setString(1, username);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username already exists: " + username);
                usernameExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernameExists;
    }




    public static boolean inserting_name_username_lname(String fname,String lname,String user_name){

        if(!checkUsernameExists(user_name)) {

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String sql = "INSERT INTO user_info (name,family_name,user_name) values (?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, fname);
                    preparedStatement.setString(2, lname);
                    preparedStatement.setString(3, user_name);

                    // Set the array to the prepared statement
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        System.out.println("this user name already exist chose another one");

        return false;

    }


    public static void inserting_password_email_bio(String user_name,String user_password,String email,String bio){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE user_info\n" +
                    "SET password = ?,\n" +
                    "    email = ?,\n" +
                    "    bio = ?\n" +
                    "WHERE user_name=?;\n";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1,user_password);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,bio);
                preparedStatement.setString(4,user_name);

                // Set the array to the prepared statement
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// i should if the user already have added this video
    public static void add_watch_later(String user_name,int video_id,String video_title){
        // Convert ArrayList to PostgreSQL array literal

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "INSERT INTO watchlater (user_name,video_id,video_title) VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setString(1,user_name);
            pstmt.setInt(2,video_id);
            pstmt.setString(3,video_title);


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//needs to get compeleted
    public static ArrayList<String> get_watch_later(int user_id){
        String query = "SELECT watchlater FROM Userinfo WHERE user_id = ?";

        ArrayList<String> watchLaterList = new ArrayList<>();
        ArrayList <String> wacthlater = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String video_title = rs.getString("video_title");
                wacthlater.add(video_title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return watchLaterList;
    }

    //this is for the list of play lists
    // i will done this later
    //use this method when user want to make another play list
    public static void add_to_playlists(int user_id,String playlist_name){
        // Convert ArrayList to PostgreSQL array literal

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "INSERT INTO list_playlist (user_id,playlist_name) VALUES (?,?)";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setInt(1,user_id);
            pstmt.setString(2,playlist_name);

            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //need to make a method to get the play list id
    public static ArrayList<Integer> get_playlist(int play_list_id){
        String query = "SELECT video_id FROM user_info WHERE user_name = ?";

        ArrayList<Integer> selected_playlist = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, play_list_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //String video_title = rs.getString("video_title");
                int video_id = rs.getInt("video_id");
                selected_playlist.add(video_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selected_playlist;
    }



// i changed this method from array of string to int and sending video id
    public static ArrayList<Integer> get_liked_list(int user_id) {
        String query = "SELECT video_id FROM liked WHERE user_id = ?";

        ArrayList<Integer> liked = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, user_id); // Set the user ID parameter

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int video_id = rs.getInt("video_id");
                //String video_title = rs.getString("video_title");
                //liked.add(video_title);
                liked.add(video_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liked;
    }


// this method will get the chanels that user have subscribed
    public static ArrayList<Integer> get_channels_id(int subscriber_id){
        String query = "SELECT channel_id FROM subscribers WHERE subscriber_id = ?";

        ArrayList<Integer> chanels = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, subscriber_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int chanel_id = rs.getInt("channel_id");
                chanels.add(chanel_id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chanels;
    }

    public static void add_to_liked(int user_id,int video_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "INSERT INTO liked (user_id,video_id) VALUES (?,?)";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setInt(1,user_id);
            pstmt.setInt(2,video_id);

            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public  static void add_subscriber(int channel_id, int subs_id ){
//chanel id is the same as the user id i will explain it more later


        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "INSERT INTO subscribers (channel_id,subscriber_id) VALUES (?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,channel_id);
            pstmt.setInt(2,subs_id);

            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    //the method down here will show the user what users have subscribed him
    public static ArrayList<Integer> get_subscriber(int user_id){

        String query = "SELECT subscriber_id FROM subscribers WHERE channel_id = ?";

        ArrayList<Integer> subscribers_id = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int subscriber_id = rs.getInt("subscriber_id");
                subscribers_id.add(subscriber_id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers_id;
    }

    public static boolean login(String inputUsername,String inputPassword){


        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT COUNT(*) FROM user_info WHERE user_name = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, inputUsername);
                statement.setString(2, inputPassword);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            System.out.println("Login successful!");
                            return true;
                        } else {
                            System.out.println("Invalid credentials. Please try again.");
                            return false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int get_user_id(String user_name){
        String query = "SELECT user_id FROM user_info WHERE user_name = ?";



        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1,user_name );
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int user_id = rs.getInt("user_id");
                return user_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String get_bio(int user_id){
        String query = "SELECT bio FROM user_info WHERE user_id = ?";



        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1,user_id );
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String bio = rs.getString("bio");
                return bio;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "no name";
    }


    //DELETE FROM Customers WHERE CustomerName = 'Alfreds Futterkiste';
// ishould remove it from file of videos too
    public static void delete_video(int video_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "DELETE FROM videos WHERE user_id = ?";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setInt(1,video_id);

            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getLastVideoId() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT video_id FROM videos ORDER BY video_id DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("video_id");
                } else {
                    System.out.println("No videos found in the table.");
                    return -1; // Or handle the absence of records as needed
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching last video_id: " + e.getMessage());
            return -1; // Handle the exception appropriately
        }
    }


    public static void add_video(int user_id,String user_name, String title){

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "INSERT INTO videos (user_id,user_name,title) VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setInt(1,user_id);
            pstmt.setString(2,user_name);
            pstmt.setString(3,title);

            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void puting_the_file_path_into_table(String path,int video_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE videos SET path = ? WHERE video_id = ? ";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setString(1,path);
            pstmt.setInt(2,video_id);

            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void increase_likes_of_video(int video_id){

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE videos\n" +
                    "SET likes = likes + 1\n" +
                    "WHERE video_id = ?;\n";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,video_id);

            // Set the array to the prepared statement


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }






    public static void decrease_like_for_video (int video_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE videos\n" +
                    "SET likes = likes - 1\n" +
                    "WHERE video_id = ?;\n";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,video_id);

            // Set the array to the prepared statement


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }






    public static void increase_view_of_video(int video_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE videos\n" +
                    "SET views = views + 1\n" +
                    "WHERE video_id = ?;\n";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,video_id);

            // Set the array to the prepared statement


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


// this will send all the users video
    public static ArrayList<Integer> video_of_user(int user_id){

        String query = "SELECT video_id FROM videos WHERE user_id = ?";

        ArrayList<Integer> videoid = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int video_id = rs.getInt("video_id");
                videoid.add(video_id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoid;


    }


    public static void making_comment(int video_id,String user_name,String comment) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "INSERT INTO comments (user_name,video_id,comment) VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql);

            // Set the array to the prepared statement
            pstmt.setString(1, user_name);
            pstmt.setInt(2, video_id);
            pstmt.setString(3, comment);


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

// to get a comment it needs to get user name the comment and the likes i need three method that return arraylist

    //how to handle a double like
    // i should add to method for deacrising the like of video and comment
    public static void increase_like_for_comment (int comment_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE comments\n" +
                    "SET likes = likes + 1\n" +
                    "WHERE comment_id = ?;\n";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,comment_id);

            // Set the array to the prepared statement


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    public static void decrease_like_for_comment (int comment_id){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE comments\n" +
                    "SET likes = likes - 1\n" +
                    "WHERE comment_id = ?;\n";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,comment_id);

            // Set the array to the prepared statement


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


// make a methid to add video path
    public static String get_video_path(int video_id){

        String query = "SELECT path FROM videos WHERE video_id = ?";



        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, video_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String path_of_video = rs.getString("video_id");
                return path_of_video;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String get_profile_pic_path(int user_id){
        String query = "SELECT profile_pic_path FROM user_info WHERE user_id = ?";



        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String path_of_profile_pic = rs.getString("profile_pic_path");
                return path_of_profile_pic;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void add_profile_pic_path(String path,int user_id){

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the SQL statement
            String sql = "UPDATE user_info SET profile_pic_path = ? WHERE user_id = ?;";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,path);
            pstmt.setInt(2,user_id);


            // Execute the insertion
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }




    



    //i should complete the sub and making new tables for the or place it in other tabled making thumbnail and correcting the sending and getting video in client make a method to find the the video name by video id or sending video by video id
// making method for getting video with video id and complete the login method

    public static void main(String[] args){
        //inserting_user_info("mmd","mmd101","hosseini","hello im a gamer at youtube",1979,"april",10);
        //add_watch_later(1,1,"fortnite");
        //add_to_playlists(1,"games");
        //add_to_liked(1,"how to make americano");
        //ArrayList <Integer> liked_list =get_channels_id(2);
        //System.out.println(liked_list);
        //add_subscriber(2,1);
       // login("sepi","1234");
        //add_video(1,"mia","vlog","D:\\youtube\\src\\main\\resources\\videos\\2.mkv");
        //delete_video(1);
        //ArrayList<Integer> vids =video_of_user(1);
        //System.out.println(vids);
        //increase_likes_of_video(3);
        //increase_view_of_video(3);
        //System.out.println(get_bio(1));
        //int n =get_user_id("sepi");
        //making_comment(1,"sepi","that was cool!");
        //add_profile_pic_path("D:\\final_project\\src\\main\\resources\\client_videos",2);
        //String path =get_profile_pic_path(1);
        //System.out.println(path);
        //increase_like_for_comment(1);
       // inserting_name_username_lname("sepanta","hos","mrbeast");
        //inserting_password_email_bio("mrbeast","12345","email","bio");
        //inserting_birth_date_and_gender(1979,"august",7,"mrbeast","man");
       // add_to_liked(1,3);
        //checkUsernameExists("mrbeast");
        //inserting_name_username_lname("sepanta","hos","mrbeast");
        }

    }

