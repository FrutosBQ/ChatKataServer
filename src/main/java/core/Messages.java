package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    @JsonProperty
    private List<Message> messages;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Messages messages = (Messages) o;

        if (nextSeq != messages.getNextSeq()) return false;
        if (this.messages != null ? !this.messages.equals(messages.getMessages()) : messages.getMessages() != null) return false;

        return true;
    }

}
