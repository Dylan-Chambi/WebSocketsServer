package server;

import org.json.JSONObject;

public class Message {
    private String name;
    private String content;

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        JSONObject message = new JSONObject(this);
        return message.toString();
    }
}
