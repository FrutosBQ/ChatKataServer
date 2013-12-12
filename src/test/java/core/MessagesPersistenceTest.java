package core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by frutos on 11/12/13.
 */
public class MessagesPersistenceTest  {

    @Test
    public void addMessagesToList() throws Exception{
        MessagesPersistence messagesPersistence = new MessagesPersistence();
        messagesPersistence.add(new Message("user_1","message_1"));
        List<Message> messagesInList = messagesPersistence.getMessagesFrom(0);
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("user_1","message_1"));
        assertThat(messages).isEqualTo(messagesInList);
    }


    @Test
    public void getLastMessagesCorrectlyTest() throws Exception{
        MessagesPersistence messagesPersistence = new MessagesPersistence();
        messagesPersistence.add(new Message("user_1","message_1"));
        messagesPersistence.add(new Message("user_2","message_2"));
        messagesPersistence.add(new Message("user_3","message_3"));
        messagesPersistence.add(new Message("user_4","message_4"));
        messagesPersistence.add(new Message("user_5","message_5"));
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("user_4","message_4"));
        messages.add(new Message("user_5","message_5"));
        List<Message> lastMessages = messagesPersistence.getMessagesFrom(3);
        assertThat(messages).isEqualTo(lastMessages);
    }

    @Test
    public void getNextSeqCorrectlyTest() throws Exception{
        MessagesPersistence messagesPersistence = new MessagesPersistence();
        messagesPersistence.add(new Message("user_1","message_1"));
        messagesPersistence.add(new Message("user_2","message_2"));
        messagesPersistence.add(new Message("user_3","message_3"));
        messagesPersistence.add(new Message("user_4","message_4"));
        messagesPersistence.add(new Message("user_5","message_5"));
        assertThat(messagesPersistence.getNextSeq()).isEqualTo(5);
    }
}
