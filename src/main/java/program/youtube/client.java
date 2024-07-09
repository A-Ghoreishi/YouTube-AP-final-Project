package program.youtube;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static program.youtube.account.hashPassword;


public class client {
    //change the path of vieo for client and for server
    static Scanner  scanner = new Scanner(System.in);

    public static void send_profile_picture(String path, int user_id) {


        try {
            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_pfp";
            out.println(message);
            JSONObject json = new JSONObject();
            json.put("user_id",user_id);
            out.print(json.toString());

            File pictureFile = new File(path);
            byte[] pictureBytes = new byte[(int) pictureFile.length()];

            OutputStream outputStream = socket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pictureFile));

            int count;
            byte[] buffer = new byte[8192]; // 8KB buffer
            while ((count = bis.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
                outputStream.flush(); // Flush after each write
            }

            bis.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void sending_video(String title,String path,String user_name,int user_id,String description){


        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "get video";
            out.println(meesage);
            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name",user_name);
            jsonParams.put("title", title);
            jsonParams.put("user_id",user_id);
            jsonParams.put("description",description);



            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            File videfile = new File(path);
            byte[] videobytes = new byte[(int) videfile.length()];
            //FileInputStream fileInputStream = new FileInputStream(videfile);
            //fileInputStream.read(videobytes);
            //fileInputStream.close();


            OutputStream outputStream = socket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(videfile));

            int count;
            byte[] buffer = new byte[8192]; // 8KB buffer
            while ((count = bis.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
                outputStream.flush(); // Flush after each write
            }

            bis.close();
            outputStream.close();
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get_video_title(int video_id){
        try {
            Socket clientSocket = new Socket("localhost",4042);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String mesage = "get_video_title";
            out.println(mesage);

            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String title = json.getString("title");


            // Send a response back to the client
            clientSocket.close();
            return title;



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get_video_username(int video_id){
        try {
            Socket clientSocket = new Socket("localhost",4042);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String mesage = "get_video_user_name";
            out.println(mesage);
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            String user_name = json.getString("user_name");


            // Send a response back to the client
            clientSocket.close();
            return user_name;



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  int get_video_like(int video_id){
        try {
            Socket clientSocket = new Socket("localhost",4042);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String message = "get_video_likes";
            out.println(message);
            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            int likes = json.getInt("likes");


            // Send a response back to the client
            clientSocket.close();
            return likes;



        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int get_video_views(int video_id){
        try {
            Socket clientSocket = new Socket("localhost",4042);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String message = "send_view";
            out.println(message);


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("video_id",video_id);

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(jsonObject.toString());

            // Read data from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            // Parse the JSON data
            JSONObject json = new JSONObject(clientData);
            int views = json.getInt("views");


            // Send a response back to the client
            clientSocket.close();
            return views;



        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public  String get_video(int video_id) throws IOException {

        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "send_video";
            out.println(meesage);
            Thread.sleep(1000);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("video_id",video_id);

            out.println(jsonObject.toString());

            InputStream inputStream = socket.getInputStream();

            String name = Integer.toString(video_id);

            String path = "D:\\final_project\\src\\main\\resources\\client_videos\\" +name+".mkv";


            // Save video data to a local file
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\final_project\\src\\main\\resources\\client_videos\\" +name+".mkv");

            byte[] buffer = new byte[8192]; // Adjust buffer size as needed
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Clean up
            fileOutputStream.close();
            inputStream.close();
            socket.close();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void sign_in(String name,String user_name,String family_name,String user_password,String bio,int birth_year,String birth_month,int birth_day){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "sign_in";
            out.println(meesage);

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("name", name);
            jsonParams.put("user_name", user_name);
            jsonParams.put("family_name", family_name);
            jsonParams.put("user_password", user_password);
            jsonParams.put("bio", bio);
            jsonParams.put("birth_year", birth_year);
            jsonParams.put("birth_month", birth_month);
            jsonParams.put("birth_day", birth_day);

            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void get_profile_picture(int user_id){
        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "send_pfp";
            out.println(meesage);
            Thread.sleep(1000);
            String name = Integer.toString(user_id);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id",user_id);
            out.println(jsonObject.toString());

            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //String videoName1 = in.readLine();
            DataInputStream in = new DataInputStream(socket.getInputStream());



            // System.out.println(videoName1);
            // Get the input stream to receive data from the server
            InputStream inputStream = socket.getInputStream();
            //System.out.println("D:\\final_project\\src\\main\\resources\\client_videos" +msg+".mkv");


            // Save video data to a local file
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\final_project\\src\\main\\resources\\client_profile_picture\\" +name+".jpg");

            byte[] buffer = new byte[8192]; // Adjust buffer size as needed
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Clean up
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean login(String user_name,String user_password){
        boolean login = false;
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "log_in";
            out.println(meesage);
            Thread.sleep(1000);

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name", user_name);
            jsonParams.put("user_password", hashPassword(user_password));

            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);
            JSONObject jsonObject = new JSONObject(serverResponse);
            login = jsonObject.getBoolean("login");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return login;
    }
    public static void sending_fname_lname_user_name(String name,String lname,String user_name){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "sign_in_name";
            out.println(meesage);
            Thread.sleep(1000);

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("name", name);
            jsonParams.put("user_name", user_name);
            jsonParams.put("family_name", lname);


            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sending_birth_dates(String user_name,int birth_year,String birth_month,int birth_day,String gender){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "sign_in_birth_dates";
            out.println(meesage);

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name",user_name);
            jsonParams.put("birth_year", birth_year);
            jsonParams.put("birth_month", birth_month);
            jsonParams.put("birth_day", birth_day);
            jsonParams.put("gender",gender);


            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send_email_password_bio(String user_name,String email,String password,String bio){
        password = hashPassword(password);

        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "sign_in_eamil,pass,bio";
            out.println(meesage);
            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name",user_name);
            jsonParams.put("email", email);
            jsonParams.put("password", password);
            jsonParams.put("bio", bio);


            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void sending_comment(String comment,String user_name,int video_id){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "sending comment";
            out.println(meesage);
            Thread.sleep(1000);
            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name",user_name);
            jsonParams.put("comment", comment);
            jsonParams.put("video_id",video_id);



            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void liking_the_video(int video_id,int user_id){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "liking the video";
            out.println(meesage);
            Thread.sleep(1000);

            // Create a JSON object with the provided data

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("video_id",video_id);
            jsonParams.put("user_id",user_id);

            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int get_user_id(String user_name){
        int user_id=0;
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "get user_id";
            out.println(meesage);
            Thread.sleep(1000);

            // Create a JSON object with the provided data

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name",user_name);
            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            JSONObject json = new JSONObject(serverResponse);
            user_id = json.getInt("user_id");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user_id;
    }

    public void send_thumbnail(int video_id, String path) {
        try (Socket socket = new Socket("localhost", 4042);
             OutputStream outputStream = socket.getOutputStream()) {

            // Send the "get_thumbnail" message
            PrintWriter out = new PrintWriter(outputStream, true);
            String message = "get_thumbnail";
            out.println(message);

            // Wait for a moment (optional)
            Thread.sleep(1000);

            // Send the video_id as raw bytes
            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // Send the JSON object as a string
            out.println(json.toString());

            File videofile = new File(path);
            byte[] buffer = new byte[8192];
            int bytesRead;

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(videofile));
            while ((bytesRead = bis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                outputStream.flush();
            }

            bis.close();

            // Send the thumbnail data
            // ... (rest of the method remains the same)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String get_thumbnail(int video_id){
        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "send_thumbnail";
            out.println(meesage);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id",video_id);
            out.print(json.toString());

            String name = Integer.toString(video_id);

            InputStream inputStream = socket.getInputStream();

            String path = "D:\\final_project\\src\\main\\resources\\client_thumbnail\\" +name+".jpg";
            // Save video data to a local file
            FileOutputStream fileOutputStream = new FileOutputStream(path);

            byte[] buffer = new byte[8192]; // Adjust buffer size as needed
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Clean up
            fileOutputStream.close();
            inputStream.close();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String get_bio(int user_id){
        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "send_bio";
            out.println(meesage);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id",user_id);
            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            JSONObject json1 = new JSONObject(clientData);
            String bio = json1.getString("bio");

            return bio;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<String> search_username(String search){
        List <String> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "search_username";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("search",search);
            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<List<String>>() {});

            // Now you have your ArrayList
            System.out.println("Deserialized ArrayList:");
            System.out.println(myList);

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return myList;
    }

    public  List<Integer> search_video(String search){
        List <Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "search_video";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("search",search);
            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<List<Integer>>() {});

            // Now you have your ArrayList
            System.out.println("Deserialized ArrayList:");
            System.out.println(myList);

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return myList;
    }

    public void add_watch_later(int video_id,int user_id){
        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "add_to_watch_later";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id",video_id);
            json.put("user_id",user_id);
            out.print(json.toString());

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public ArrayList<Integer> get_watch_later(int user_id){
        ArrayList <Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_watch_later";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id",user_id);

            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            System.out.println("Received login-in data from client: " + clientData);
            json = new JSONObject(clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});


            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return myList;
    }

    public void make_new_play_list(int user_id,String name) {

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "make_new_play_list";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id", user_id);
            json.put("name",name);


            out.print(json.toString());


            // Deserialize the JSON array to an ArrayList
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void add_video_to_play_list(String name,int user_id,int video_id){
        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "add_video_to_play_list";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id", user_id);
            json.put("name",name);
            json.put("video_id",video_id);


            out.print(json.toString());


            // Deserialize the JSON array to an ArrayList
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> get_play_list(int user_id, String watch_list_name){
        ArrayList <String> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_playlists";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id",user_id);
            json.put("name",watch_list_name);


            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<String>>() {});




        }catch (Exception e){
            e.printStackTrace();
        }
        return myList;
    }

    public ArrayList <Integer> get_video_playlist(int user_id,String watch_list_name){

        ArrayList <Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_video_playlist";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id",user_id);
            json.put("name",watch_list_name);


            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});




        }catch (Exception e){
            e.printStackTrace();
        }
        return myList;

    }

    public ArrayList <Integer> get_subscriber(int user_id){
        ArrayList <Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_subscriber";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id",user_id);


            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});




        }catch (Exception e){
            e.printStackTrace();
        }
        return myList;
    }

    public void add_subscriber(int user_id,int channel_id){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "add_to_subscriber";
            out.println(meesage);
            Thread.sleep(1000);

            // Create a JSON object with the provided data

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("channel_id",channel_id);
            jsonParams.put("user_id",user_id);

            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());
            System.out.println("sent");

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            socket.close();
            System.out.println("Server response: " + serverResponse);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList <Integer> get_liked_list(int user_id) {
        ArrayList<Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_liked_list";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id", user_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return myList;

    }

    public ArrayList<String> client_get_comment(int video_id){
        ArrayList<String> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "send_comment";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<String>>() {});

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    public ArrayList <String> get_username_of_comments(int video_id){
        ArrayList<String> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "send_username_comment";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<String>>() {});

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    public ArrayList<Integer> get_likes_of_comment(int video_id){
        ArrayList<Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "send_likes_comment";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    public void client_delete_video(int video_id){
        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "delete_video";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList <String> client_get_name(int user_id){
        ArrayList<String> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "send_name_familyname";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id", user_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<String>>() {});

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    public ArrayList <Integer> client_videos_of_user(int user_id){
        ArrayList<Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "send_videos_of_user";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id", user_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    public void increase_view_video(int video_id){
        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "increase_view";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("video_id", video_id);

            // write data to socket
            out.print(json.toString());


            // read data from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void change_bio(String bio,int user_id){

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "change_bio";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("bio",bio);
            json.put("user_id",user_id);
            out.print(json.toString());

            // Deserialize the JSON array to an ArrayList

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> get_channel_id(int user_id){
        ArrayList <Integer> myList = new ArrayList<>();

        try {

            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_channel";
            out.println(message);

            Thread.sleep(1000);

            JSONObject json = new JSONObject();
            json.put("user_id",user_id);


            out.print(json.toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientData = reader.readLine();
            socket.close();
            System.out.println("Received login-in data from client: " + clientData);

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize the JSON array to an ArrayList
            myList = objectMapper.readValue(clientData, new TypeReference<ArrayList<Integer>>() {});




        }catch (Exception e){
            e.printStackTrace();
        }
        return myList;
    }





//things to do: completing the apis about the whatch list not letting user like somthing twice and handeling race condition  and chck the method about sending profile pic rename where videos save and api for getting comment from the server
//make api for search:done




    public static void main(String[] args) throws IOException, InterruptedException {
        //int num = 1;
        //send_video("C:\\Users\\Sepanta\\Downloads\\@movieo_bot.Black.Bullet.E02.720p.BluRay.@movieo_bot.mkv",num +1);
        //get_video("set");
        //sign_in("John Doe","johndoe","Doe","securepassword","A passionate developer",1990,"January",15);
       //send_profile_picture("C:\\Users\\Sepanta\\Downloads\\won.jpg",1);
        //requestVideo("video.mkv");
        //get_profile_picture("got");
        //login("sepi","1234");
        //sending_fname_lname_user_name("christian","bale","patric_bateman");
        //sending_birth_dates("patric_bateman",1969,"january",18,"man");
        //send_email_password_bio("patric_bateman","nvjkn@gmail.com","vfknk;v","hello there -obi-one");
        client client = new client();
        client.send_thumbnail(3,"C:\\Users\\Sepanta\\Downloads\\won.jpg");
        //login("patric_bateman","vfknk;v");
        //client.sending_comment("lame video","mrbeast",1);
        //client.liking_the_video(3,1);
        //sending_fname_lname_user_name("sepanta","hos","mrbeast1");
        //System.out.println(client.get_user_id("mrbeast"));
       //client.sending_video("vlog3","C:\\Users\\Sepanta\\Downloads\\ccc8ac35723faa7986342aff76a0eda68350123-360p.mp4","mrbeast",5);
       // client.send_thumbnail(3,"C:\\Users\\Sepanta\\Downloads\\won.jpg");
        //client.sending_video("ldn","C:\\Users\\Sepanta\\Downloads\\@movieo_bot.Black.Bullet.E01.720p.BluRay.@movieo_bot.mkv","mia");
        //client.get_thumbnail(3);


    }




}
