package server;

import  org.java_websocket.WebSocket;

public interface ICanalComunicacion { //Mediator
    void sendAll(WebSocket sender, String video);
}
