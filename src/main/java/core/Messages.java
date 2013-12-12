package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    @JsonProperty
    private List<Message> messages = new ArrayList<Message>();
    @JsonProperty
    private int nextSeq;

    private Messages(){}

    public int getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Messages(int nextSeq, List<Message> messagesFrom){
        this.nextSeq = nextSeq;
        this.messages = messagesFrom;

    }



}
