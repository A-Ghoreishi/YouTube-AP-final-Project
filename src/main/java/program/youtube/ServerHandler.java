package program.youtube;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static program.youtube.database.*;


class ServerHandler implements Runnable {
    static database database = new database();
    private final Socket clientSocket;
    private final Lock lock = new ReentrantLock();

    public ServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Get input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read client input
            String clientInput;

            while ((clientInput = in.readLine()) != null) {

                switch (clientInput) {
                    case "get video" -> {
                        System.out.println("get_video");
                        get_video(clientSocket);

                    }
                    case "send_video" -> {
                        server_video_sender(clientSocket);
                        out.println("sent");
                    }
                    case "get_pfp" -> {
                        System.out.println("pfp");
                        get_pfp(clientSocket);
                    }
                    case "send_pfp" ->{

                        System.out.println("send_pfp");
                        server_send_pfp(clientSocket);
                    }
                    case "log_in" ->{
                        System.out.println("login");
                        server_log_in(clientSocket);
                    }
                    case "sign_in_name" ->{
                        System.out.println("sign_in_name");
                        sign_in_get_name(clientSocket);
                    }
                    case "sign_in_birth_dates" ->{
                        System.out.println("birth");
                        sign_in_get_birth_dates(clientSocket);
                    }
                    case "sign_in_eamil,pass,bio" ->{
                        System.out.println("email");
                        sign_in_get_email_pass_bio(clientSocket);
                    }
                    case "sending comment" ->{
                        System.out.println("getting the comment");
                        server_add_comment(clientSocket);
                        System.out.println("done getting the comment");
                    }
                    case "liking the video" ->{
                        System.out.println("liking the video");
                        server_liking_video(clientSocket);
                        System.out.println("done");
                    }
                    case "get user_id" ->{
                        System.out.println("sending the user_id");
                        send_user_id(clientSocket);
                    }
                    case "get_thumbnail" ->{
                        System.out.println("get thumbnail");
                        get_thumbnail(clientSocket);
                    }
                    case "send_thumbnail" ->{
                        System.out.println("send_thumbnail");
                        server_send_thumbnail(clientSocket);
                    }
                    case "send_bio" ->{
                        System.out.println("send bio");
                        send_bio(clientSocket);
                    }
                    case "change_bio" ->{
                        System.out.println("change bio");
                        server_change_bio(clientSocket);
                    }
                    case "search_video" ->{
                        System.out.println("searching video");
                        server_search_video(clientSocket);
                    }
                    case "search_user_name" ->{
                        System.out.println("searching user_name");
                        server_search_user_name(clientSocket);
                    }
                    case "get_video_title" ->{
                        System.out.println("send title");
                        server_send_video_title(clientSocket);

                    }
                    case "get_video_user_name" -> {
                        System.out.println("send_video_user_name");
                        server_send_video_user_name(clientSocket);
                    }
                    case "get_video_likes" ->{
                        System.out.println("send likes");
                        server_send_video_likes(clientSocket);
                    }
                    case "send_view" ->{
                        System.out.println("send_view");
                        server_send_views();
                    }
                    case "increase_view" -> {
                        System.out.println("increase view");
                        increase_view_videos(clientSocket);
                    }
                    case "send_videos_of_user" ->{
                        System.out.println("videos of user");
                        server_videos_of_user(clientSocket);
                    }
                    case "send_name_familyname" ->{
                        System.out.println("send names");
                        server_send_name(clientSocket);
                    }
                    case "delete_video" -> {
                        System.out.println("delete video");
                        server_delete_video(clientSocket);
                    }
                    case "send_likes_comment" -> {
                        System.out.println("send like comments");
                        server_send_comment_like(clientSocket);
                    }
                    case "send_username_comment" ->{
                        System.out.println("user name of comments");
                        server_send_username_comment(clientSocket);
                    }
                    case "send_comment" -> {
                        System.out.println("send the comment");
                        server_send_comment(clientSocket);
                    }
                    case "get_liked_list" -> {
                        System.out.println("get comments");
                        server_get_liked_list(clientSocket);
                    }

                    case "add to subscriber" -> {
                        System.out.println("add_to_subscriber");
                        add_subscriber(clientSocket);
                    }
                    case "get_subscriber" -> {
                        System.out.println("get subscriber");
                        server_send_subscriber(clientSocket);
                    }
                    case "get_video_playlist" -> {
                        System.out.println("get video of playlist");
                        server_send_video_playlist(clientSocket);
                    }
                    case "get_playlists" -> {
                        System.out.println("sending play list");
                        server_send_play_list(clientSocket);
                    }
                    case "add_video_to_play_list" -> {
                        System.out.println("add_video_to_play_list");
                        server_add_video_play_list(clientSocket);
                    }
                    case "make_new_play_list" -> {
                        System.out.println("make new play list");
                        server_make_new_watch_list(clientSocket);
                    }
                    case "get_watch_later" -> {
                        System.out.println("get_watch_later");
                        server_get_watch_later(clientSocket);
                    }
                    case "add_to_watch_later" -> {
                        System.out.println("add_to_watch_later");
                        server_add_to_watch_later(clientSocket);
                    }
                    case "search_username" -> {
                        System.out.println("search_username");
                        server_search_user_name(clientSocket);
                    }
                    case "get_channel" -> {
                        System.out.println("get_channel");
                        server_send_channel_id(clientSocket);
                    }
                }

                // Process client input (you can customize this part)
                //String response = "Hello, client!"; // Your custom response
// i should add a method for changing the bio
                // Send response back to client
                //out.println(response);
            }

            // Clean up
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void get_thumbnail(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();

            // Parse the JSON data


            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Read video_id from the client


            String name = Integer.toString(database.getLastVideoId());
            String path = "D:\\final_project\\src\\main\\resources\\server_thumbnail\\" + name + ".jpg";

            lock.lock();
            database.add_thumbnail( path);
            lock.unlock();


            // Receive thumbnail data and save it to the file
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            while ((bytesRead = inputStream.read(buffer)) != -1) {

                fileOutputStream.write(buffer, 0, bytesRead);
            }


            fileOutputStream.close();
            inputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public  void get_video(Socket clientSocket){

        try {

            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String userName = json.getString("user_name");
            String title = json.getString("title");
            int user_id = json.getInt("user_id");
            String description = json.getString("description");
            database database = new database();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);
            lock.lock();
            add_video(user_id,userName,title,description);
            lock.unlock();


            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);

            int videoid = database.getLastVideoId();
            String name = Integer.toString(videoid);
            String save_path = "D:\\final_project\\src\\main\\resources\\server_videos\\"+name+".mkv";
            lock.lock();
            database.putting_the_file_path_into_table(save_path,videoid);
            lock.unlock();
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int byteread;
            FileOutputStream fileOutputStream = new FileOutputStream(save_path);


            while ((byteread = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,byteread);
            }


            fileOutputStream.close();
            inputStream.close();
            clientSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_video_user_name(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name",database.get_video_user_name(video_id));

            // Write data to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void server_send_video_title(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();

            jsonParams.put("title",database.get_video_title(video_id));

            // Write data to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void server_send_video_likes(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("likes",database.get_video_likes(video_id));

            // Write data to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void server_send_views(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("views",database.get_video_views(video_id));

            // Write data to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void server_video_sender(Socket clientSocket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String clientData = reader.readLine();
        System.out.println("Received login-in data from client: " + clientData);

        JSONObject json = new JSONObject(clientData);
        int video_id = json.getInt("video_id");

        String path = get_video_path(video_id);

        FileInputStream videoFile = new FileInputStream(path);

        while (true) {
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF("msg");

            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = videoFile.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            videoFile.close();
            clientSocket.close();


        }
    }
    /* FileInputStream videoFile = new FileInputStream("C:\\Users\\Sepanta\\Downloads\\@movieo_bot.Black.Bullet.E02.720p.BluRay.@movieo_bot.mkv");

        while (true) {
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF("msg");

            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = videoFile.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            videoFile.close();
            clientSocket.close();
        }*/



    public static void server_send_pfp(Socket clientSocket) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String clientData = reader.readLine();
        System.out.println("Received login-in data from client: " + clientData);

        JSONObject json = new JSONObject(clientData);
        int user_id = json.getInt("user_id");

        FileInputStream videoFile = new FileInputStream(database.get_profile_pic_path(user_id));

        while (true) {
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = videoFile.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            videoFile.close();
            clientSocket.close();
        }
    }
//check methods about sending pfp
public void get_pfp(Socket clientSocket) {
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Read JSON data from the client
        String clientData = reader.readLine();
        JSONObject json = new JSONObject(clientData);
        int userId = json.getInt("user_id"); // Assuming the key is "user_id"

        // Save profile picture to a file (e.g., user123.jpg)
        String profilePicPath = "D:\\final_project\\src\\main\\resources\\server_profile_pictures\\" + userId + ".jpg";

        lock.lock();
        database.add_profile_pic_path(profilePicPath,userId);
        lock.unlock();

        FileOutputStream fileOutputStream = new FileOutputStream(profilePicPath);
        byte[] buffer = new byte[8192];
        int bytesRead;

        InputStream inputStream = clientSocket.getInputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
        inputStream.close();
        clientSocket.close();

        System.out.println("Profile picture saved for user " + userId + ": " + profilePicPath);
    } catch (Exception e) {
        e.printStackTrace();
    }
}









    public  void sign_in_get_name(Socket clientSocket) throws IOException {
        // Read data from the client
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received sign-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String name = json.getString("name");
            String userName = json.getString("user_name");
            String familyName = json.getString("family_name");

            database database = new database();
            lock.lock();
            database.inserting_name_username_lname(name,familyName,userName);
            lock.lock();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);

            // Insert data into the database (use your actual method here)
            // Example (assuming you have a method called insertIntoDatabase):
            // insertIntoDatabase(name, userName, familyName, password, bio, birthYear, birthMonth, birthDay);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);
        } catch (IOException e) {
        e.printStackTrace();
        }
    }



    public  void sign_in_get_birth_dates(Socket clientSocket) throws IOException {
        // Read data from the client
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received sign-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String user_name = json.getString("user_name");
            int birth_day = json.getInt("birth_day");
            String birth_month = json.getString("birth_month");
            int birth_year = json.getInt("birth_year");
            String gender = json.getString("gender");

            database database = new database();

            lock.lock();
            database.inserting_birth_date_and_gender(birth_year,birth_month,birth_day,user_name,gender);
            lock.unlock();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);

            // Insert data into the database (use your actual method here)
            // Example (assuming you have a method called insertIntoDatabase):
            // insertIntoDatabase(name, userName, familyName, password, bio, birthYear, birthMonth, birthDay);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void sign_in_get_email_pass_bio(Socket clientSocket){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received sign-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String user_name = json.getString("user_name");
            String email = json.getString("email");
            String password = json.getString("password");
            String bio = json.getString("bio");


            database database = new database();
            lock.lock();
            inserting_password_email_bio(user_name,password,email,bio);
            lock.unlock();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);

            // Insert data into the database (use your actual method here)
            // Example (assuming you have a method called insertIntoDatabase):
            // insertIntoDatabase(name, userName, familyName, password, bio, birthYear, birthMonth, birthDay);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void server_log_in(Socket clientSocket){
        try {
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String userName = json.getString("user_name");
            String password = json.getString("user_password");
            database database = new database();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);
            lock.lock();
            boolean login = database.login(userName,password);
            lock.unlock();
            // Insert data into the database (use your actual method here)
            // Example (assuming you have a method called insertIntoDatabase):
            // insertIntoDatabase(name, userName, familyName, password, bio, birthYear, birthMonth, birthDay);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login",login);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonObject);
            writer.println("Server received your sign-in data: " + clientData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  void server_add_comment(Socket clientSocket){
        try {
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String userName = json.getString("user_name");
            String comment = json.getString("comment");
            int video_id = json.getInt("video_id");
            database database = new database();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);
            lock.lock();
            database.making_comment(video_id,userName,comment);
            lock.unlock();
            // Insert data into the database (use your actual method here)
            // Example (assuming you have a method called insertIntoDatabase):
            // insertIntoDatabase(name, userName, familyName, password, bio, birthYear, birthMonth, birthDay);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void server_liking_video(Socket clientSocket){
        try {
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            int user_id = json.getInt("user_id");
            int video_id = json.getInt("video_id");

            database database = new database();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);
            lock.lock();
            database.increase_likes_of_video(video_id);
            database.add_to_liked(user_id,video_id);
            lock.unlock();
            // Insert data into the database (use your actual method here)
            // Example (assuming you have a method called insertIntoDatabase):
            // insertIntoDatabase(name, userName, familyName, password, bio, birthYear, birthMonth, birthDay);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send_user_id(Socket clientSocket) {
        try {
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String user_name = json.getString("user_name");

            int user_id = database.get_user_id(user_name);

            json.put("user_id",user_id);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void server_send_thumbnail(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            JSONObject json = new JSONObject(clientData);
            int video_id = json.getInt("video_id");



            FileInputStream videoFile = new FileInputStream(database.get_thumbnail_path(video_id));

            while (true) {
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                out.writeUTF("msg");

                OutputStream outputStream = clientSocket.getOutputStream();

                byte[] buffer = new byte[8192];
                int bytesRead;

                while ((bytesRead = videoFile.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                videoFile.close();
                clientSocket.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send_bio(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("bio",database.get_bio(user_id));

            // Write data to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void server_search_video(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            String search = jsonObject.getString("search");

            List<Integer> videos = database.search_video(search);



            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);


            System.out.println("sent");

            // Read the server's response

            clientSocket.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_search_user_name(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            String search = jsonObject.getString("search");


            List<String> username =  database.searchByUsername(search);


            // Create a JSON object with the provided data

            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(username);

            // Write data to the server
            writer.println(json);


            System.out.println("sent");

            // Read the server's response

            clientSocket.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_add_to_watch_later(Socket clientSocket){

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);
            JSONObject json = new JSONObject(clientData);

            int video_id = json.getInt("video_id");
            int user_id = json.getInt("user_id");
            lock.lock();
            add_watch_later(video_id,user_id);
            lock.unlock();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_get_watch_later(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");

            ArrayList<Integer> videos = get_watch_later(user_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);


            System.out.println("sent");

            // Read the server's response

            clientSocket.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_make_new_watch_list(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");
            String name = jsonObject.getString("name");

            lock.lock();
            database.add_to_playlists(user_id,name);
            lock.unlock();

            System.out.println("sent");

            clientSocket.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_add_video_play_list(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");
            String name = jsonObject.getString("name");
            int video_id = jsonObject.getInt("video_id");

            lock.lock();
            database.add_video_to_play_list(video_id,user_id,name);
            lock.unlock();

            System.out.println("sent");

            clientSocket.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_play_list(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");

            ArrayList<String> videos = database.get_play_lists(user_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_video_playlist(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");
            String name = jsonObject.getString("name");

            ArrayList<Integer> videos = database.get_video_playlist(user_id,name);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_subscriber(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");


            ArrayList<Integer> user = database.get_subscriber(user_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(user);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // i should make this for the client

    public void server_send_channel_id(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");


            ArrayList<Integer> user = database.get_channels_id(user_id);


            ObjectMapper objectMapper = new ObjectMapper();

            // Create a JSON object with the provided data


            // Create an ObjectMapper

            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(user);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_get_liked_list(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");


            ArrayList<Integer> user = database.get_liked_list(user_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(user);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void add_subscriber(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");
            int channel_id = jsonObject.getInt("channel_id");

            lock.lock();
            database.add_subscriber(channel_id,user_id);
            lock.unlock();

            // Create a JSON object with the provided data


            // Serialize the ArrayList to JSON

            // Write data to the server
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void server_send_comment(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            ArrayList<String> videos = database.get_comment(video_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_username_comment(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            ArrayList<String> videos = database.get_username_comment(video_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_comment_like(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("video_id");


            ArrayList<Integer> videos = database.get_comments_like(video_id);

            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(videos);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_delete_video(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            clientSocket.close();
            int video_id = jsonObject.getInt("video_id");

            lock.lock();
            database.delete_video(video_id);
            lock.unlock();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_send_name(Socket clientSocket){
        ArrayList <String> names = new ArrayList<>();
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");

            names.add(database.get_name(user_id));
            names.add(database.get_family_name(user_id));


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(names);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");


        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void server_videos_of_user(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");


            ArrayList<Integer> user = database.video_of_user(user_id);


            // Create a JSON object with the provided data


            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Serialize the ArrayList to JSON
            String json = objectMapper.writeValueAsString(user);

            // Write data to the server
            writer.println(json);
            clientSocket.close();


            System.out.println("sent");

            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void increase_view_videos(Socket clientSocket){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            clientSocket.close();
            int video_id = jsonObject.getInt("video_id");

            lock.lock();
            database.increase_view_of_video(video_id);
            lock.unlock();
            // Create an ObjectMapper


            // Read the server's response




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void server_change_bio(Socket clientSocket){

        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            JSONObject jsonObject = new JSONObject(clientData);
            int user_id = jsonObject.getInt("user_id");
            String bio  = jsonObject.getString("bio");

            lock.lock();
            database.change_bio(bio,user_id);
            lock.unlock();

            clientSocket.close();

            System.out.println("sent");


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    





}


