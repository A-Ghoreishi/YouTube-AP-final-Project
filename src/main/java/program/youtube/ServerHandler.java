package program.youtube;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

import static program.youtube.database.*;


class ServerHandler implements Runnable {
    static database database = new database();
    private final Socket clientSocket;

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
                    case "sign_in" -> {
                        System.out.println("on");
                        server_sign_in(clientSocket);
                        System.out.println("got the info");
                    }
                    case "get_pfp" -> {
                        System.out.println("pfp");
                        String name = in.readLine();
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
                        get_comment(clientSocket);
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
                }

                // Process client input (you can customize this part)
                //String response = "Hello, client!"; // Your custom response

                // Send response back to client
                //out.println(response);
            }

            // Clean up
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void get_video(Socket clientSocket){
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
            database database = new database();
            //inserting_user_info(name,userName,familyName,password,bio,birthYear,birthMonth,birthDay);
            add_video(user_id,userName,title);


            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Server received your sign-in data: " + clientData);

            int videoid=database.getLastVideoId();
            String name = Integer.toString(videoid);
            String save_path = "D:\\final_project\\src\\main\\resources\\server_videos\\"+name+".mkv";
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int byteread;
            FileOutputStream fileOutputStream = new FileOutputStream(save_path);
            database.puting_the_file_path_into_table(save_path,videoid);

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
            int video_id = jsonObject.getInt("user_id");


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
            int video_id = jsonObject.getInt("user_id");


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
    public void server_send_likes(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            JSONObject jsonObject = new JSONObject(clientData);
            int video_id = jsonObject.getInt("user_id");


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
            int video_id = jsonObject.getInt("user_id");


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



        FileInputStream videoFile = new FileInputStream(get_profile_pic_path(user_id));

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
    }
//check methods about sending pfp
    public static void get_pfp(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            JSONObject json = new JSONObject(clientData);
            int user_id = json.getInt("user_id");
            String name = Integer.toString(user_id);

            String path = "D:\\final_project\\src\\main\\resources\\server_profile_pictures\\" + name + ".jpg";
            add_profile_pic_path(path,user_id);


            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[8192];
            int byteread;

            FileOutputStream fileOutputStream = new FileOutputStream(path);

            while ((byteread = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, byteread);
            }
            fileOutputStream.close();
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    private static void server_sign_in(Socket clientSocket) {
        try {
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received sign-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String name = json.getString("name");
            String userName = json.getString("user_name");
            String familyName = json.getString("family_name");
            String password = json.getString("user_password");
            String bio = json.getString("bio");
            int birthYear = json.getInt("birth_year");
            String birthMonth = json.getString("birth_month");
            int birthDay = json.getInt("birth_day");
            database database = new database();
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

    public static void sign_in_get_name(Socket clientSocket) throws IOException {
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
            inserting_name_username_lname(name,familyName,userName);
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



    public static void sign_in_get_birth_dates(Socket clientSocket) throws IOException {
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
            inserting_birth_date_and_gender(birth_year,birth_month,birth_day,user_name,gender);
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

    public static void sign_in_get_email_pass_bio(Socket clientSocket){
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
            inserting_password_email_bio(user_name,password,email,bio);
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

    public static void server_log_in(Socket clientSocket){
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
            database.login(userName,password);
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
    public static void get_comment(Socket clientSocket){
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
            making_comment(video_id,userName,comment);
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
            increase_likes_of_video(video_id);
            add_to_liked(user_id,video_id);
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

            database database = new database();
            int user_id = get_user_id(user_name);
            json.put("user_id",user_id);

            // Send a response back to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get_thumbnail(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            JSONObject json = new JSONObject(clientData);
            int video_id = json.getInt("video_id");
            String name = Integer.toString(video_id);

            String path = "D:\\final_project\\src\\main\\resources\\server_thumbnail\\" + name + ".jpg";
            //add_profile_pic_path(path,video_id);
            database.add_thumbnail(video_id,path);

            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[8192];
            int byteread;

            FileOutputStream fileOutputStream = new FileOutputStream(path);

            while ((byteread = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, byteread);
            }
            fileOutputStream.close();
            inputStream.close();


        } catch (Exception e) {
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

}


