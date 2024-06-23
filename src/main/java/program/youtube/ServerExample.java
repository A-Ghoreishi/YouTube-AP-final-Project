package program.youtube;

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class ServerExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server listening on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Read data from the client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientData = reader.readLine();
                System.out.println("Received from client: " + clientData);

                // Parse the JSON data
                JSONObject json = new JSONObject(clientData);
                String name = json.getString("name");
                String userName = json.getString("user_name");
                String family_name = json.getString("family_name");
                String password = json.getString("user_password");
                String bio = json.getString("bio");
                int birth_year = json.getInt("birth_year");
                String birth_month = json.getString("birth_month");
                int birth_day= json.getInt("birth_day");
                System.out.println(name +";;"+userName+";;"+family_name+";;"+password+bio+birth_day+";;"+birth_month+";;"+birth_year);
                // Extract other fields as needed

                // Process the data (e.g., insert into the database)

                // Send a response back to the client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("Server received your data: " + clientData);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
