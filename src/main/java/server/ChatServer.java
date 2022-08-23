package server;


import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class ChatServer extends WebSocketServer implements ICanalComunicacion {
    List<WebSocket> chatRoomClients = new ArrayList<>();

    public ChatServer(String host, int port) {
        super(new InetSocketAddress(host, port));
    }

    @Override
    public void onOpen(WebSocket webSocketClient, ClientHandshake handshake) {
        chatRoomClients.add(webSocketClient);
        System.out.println(webSocketClient.getRemoteSocketAddress() + " connected");
    }

    @Override
    public void onClose(WebSocket webSocketClient, int code, String reason, boolean remote) {
        chatRoomClients.remove(webSocketClient);
        System.out.println(webSocketClient.getRemoteSocketAddress() + " disconnected");
    }

    @Override
    public void onMessage(WebSocket webSocketClient, String message) {
        JSONObject json = new JSONObject(message);
        String sender = json.getString("name");
        String content = json.getString("content");
        System.out.println(sender + ": " + content);
        sendAll(webSocketClient, message);
    }

    @Override
    public void onError(WebSocket conn, @NotNull Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(100);
    }

    @Override
    public void sendAll(WebSocket sender, String message) {
        for (WebSocket observer : chatRoomClients) {
            if (observer != sender) {
                observer.send(message);
            }
        }
    }
}
