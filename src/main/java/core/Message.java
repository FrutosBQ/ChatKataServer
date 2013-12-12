package core;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty
    private String nick;
    @JsonProperty
    private String message;

    private Message(){}

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String username, String content) {
        this.nick = username;
        this.message = content;
    }

    public boolean equals(Object obj) {
        Message message = (Message) obj;
        return (this.nick.equals(message.nick) && this.message.equals(message.message));
    }
}
