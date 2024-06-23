package program.youtube;

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class ClientExample {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);

            // Create a JSON object with the provided data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("name", "John Doe");
            jsonParams.put("user_name", "johndoe");
            jsonParams.put("family_name", "Doe");
            jsonParams.put("user_password", "securepassword");
            jsonParams.put("bio", "A passionate developer");
            jsonParams.put("birth_year", 1990);
            jsonParams.put("birth_month", "January");
            jsonParams.put("birth_day", 15);

            // Write data to the server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(jsonParams.toString());

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = reader.readLine();
            System.out.println("Server response: " + serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
