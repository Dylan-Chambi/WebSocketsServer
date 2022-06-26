package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        ChatServer server = new ChatServer(3000);
        server.start();
        System.out.println("WebSocket Server started on port: " + server.getPort());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        while (!input.equals("exit")) {
            Message message = new Message("WebSocket Server", input);
            server.broadcast(message.toString());
            input = reader.readLine();
        }
        server.stop();
    }
}
