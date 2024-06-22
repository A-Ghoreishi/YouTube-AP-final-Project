package program.youtube;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class client {
    //change the path of vieo for client and for server
    static Scanner  scanner = new Scanner(System.in);



    public static void send_video(String path,int video_id){
        String name = Integer.toString(video_id);

        try{
            Socket socket = new Socket("localhost",4042);


            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String meesage = "getfile";
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

    public static void get_file(String videoName) throws IOException {

        try {
            Socket socket = new Socket("localhost",4042);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String meesage = "sendfile";
            out.println(meesage);

            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //String videoName1 = in.readLine();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String msg = in.readUTF();
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





    public static void main(String[] args) throws IOException {
        //int num = 1;
        //send_video("C:\\Users\\Sepanta\\Downloads\\@movieo_bot.Black.Bullet.E02.720p.BluRay.@movieo_bot.mkv",num +1);
        get_file("set");
        //requestVideo("video.mkv");

    }




}
