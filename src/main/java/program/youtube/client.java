package program.youtube;

import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class client {
    //change the path of vieo for client and for server
    static Scanner  scanner = new Scanner(System.in);

    public static void send_profile_picture(String path, int user_id) {
        String name = Integer.toString(user_id);

        try {
            Socket socket = new Socket("localhost", 4042);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = "get_pfp";
            out.println(message);
            out.println(name);

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



    // i should check on this method if it works
    public static void send_video(String path,int video_id){
        String name = Integer.toString(video_id);

        try{
            Socket socket = new Socket("localhost",4042);


            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "get_pfp";
            out.println(meesage);
            out.println(name);

            File videfile =new File(path) ;
            byte[] videobytes = new byte[(int)videfile.length()];
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



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void get_video(String videoName) throws IOException {

        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "sendfile";
            out.println(meesage);

            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //String videoName1 = in.readLine();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String msg = in.readUTF();
            //replace it with the real name
            System.out.println(msg);


            // System.out.println(videoName1);
            // Get the input stream to receive data from the server
            InputStream inputStream = socket.getInputStream();
            System.out.println("D:\\final_project\\src\\main\\resources\\client_videos" +msg+".mkv");


            // Save video data to a local file
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\final_project\\src\\main\\resources\\client_videos\\" +msg+".mkv");

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
        }
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

    public static void get_profile_picture(String name){
        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "send_pfp";
            out.println(meesage);
            out.println(name);

            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //String videoName1 = in.readLine();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String msg = in.readUTF();
            //replace it with the real name
            System.out.println(msg);


            // System.out.println(videoName1);
            // Get the input stream to receive data from the server
            InputStream inputStream = socket.getInputStream();
            //System.out.println("D:\\final_project\\src\\main\\resources\\client_videos" +msg+".mkv");


            // Save video data to a local file
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\final_project\\src\\main\\resources\\client_profile_picture\\" +msg+".jpg");

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
        }
    }

    public static void login(String user_name,String user_password){
        try {
            Socket socket = new Socket("localhost", 4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "log_in";
            out.println(meesage);

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("user_name", user_name);
            jsonParams.put("user_password", user_password);

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







    public static void main(String[] args) throws IOException {
        //int num = 1;
        //send_video("C:\\Users\\Sepanta\\Downloads\\@movieo_bot.Black.Bullet.E02.720p.BluRay.@movieo_bot.mkv",num +1);
        //get_video("set");
        //sign_in("John Doe","johndoe","Doe","securepassword","A passionate developer",1990,"January",15);
       //send_profile_picture("C:\\Users\\Sepanta\\Downloads\\won.jpg",1);
        //requestVideo("video.mkv");
        //get_profile_picture("got");
        //login("sepi","1234");
    }




}
