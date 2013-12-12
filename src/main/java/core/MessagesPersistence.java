package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frutos on 11/12/13.
 */
public class MessagesPersistence {
    private List<Message> messageList;
    private int nextSeq;

    public int getNextSeq() {
        return nextSeq = messageList.size();
    }

    public MessagesPersistence(){
        messageList = new ArrayList<Message>();
    }

    public void add(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessagesFrom(int index){
        return messageList.subList(index, messageList.size());
    }
}
