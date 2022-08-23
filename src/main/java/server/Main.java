package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String HOST = InetAddress.getLocalHost().getHostAddress();
        int PORT = 6000;

        System.out.print("Server IP [" + HOST + "]: ");
        String ip = reader.readLine();
        if (!ip.isEmpty()) HOST = ip;
        System.out.print("Server Port [" + PORT + "]: ");
        String port = reader.readLine();
        if (!port.isEmpty()) PORT = Integer.parseInt(port);

        ChatServer server = new ChatServer(HOST, PORT);
        server.start();
        System.out.println("WebSocket Server started at: " + HOST + ":" + PORT);


        String input = reader.readLine();
        while (!input.equals("exit")) {
            Message message = new Message("WebSocket Server", input);
            server.broadcast(message.toString());
            input = reader.readLine();
        }
        server.stop();
    }
}
