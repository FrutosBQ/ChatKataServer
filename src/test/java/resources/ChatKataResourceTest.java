package resources;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.yammer.dropwizard.testing.ResourceTest;
import configuration.Constants;
import core.Message;
import core.Messages;
import core.MessagesPersistence;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ChatKataResourceTest extends ResourceTest {
    private ChatKataResource resource;
    private MessagesPersistence messagesPersistenceMock;

    @Override
    protected void setUpResources() {
        messagesPersistenceMock = mock(MessagesPersistence.class);
        resource = new ChatKataResource(messagesPersistenceMock);
        addResource(resource);
    }

    @Test
    public void postReturnCorrectStatusFromCorrectMessageTest() throws Exception{
        Message messageToSend = new Message("Username", "Hello World!");
        ClientResponse response = postMessage(messageToSend);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void postReturnUncorrectedStatusFromCorrectMessageTest() throws Exception{
        ClientResponse response = postMessage(null);
        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void postAddMessagesToPersistence() throws Exception{
        Message messageToSend = new Message("Username", "Hello World!");
        postMessage(messageToSend);
        verify(messagesPersistenceMock).add(messageToSend);
    }


    @Test
    public void getReturnLastMessagesCorrectly() throws Exception{
        getMessages(0);
        verify(messagesPersistenceMock).getMessagesFrom(0);
    }

    @Test
    public void getReturnNextSeqCorrectly() throws Exception{
        getMessages(0);
        verify(messagesPersistenceMock).getNextSeq();
    }

    @Test
    public void getTheCorrectedMessages() throws Exception{
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("user_1","message_1"));
        messages.add(new Message("user_2","message_2"));
        messages.add(new Message("user_3","message_3"));
        messages.add(new Message("user_4","message_4"));
        messages.add(new Message("user_5","message_5"));
        reset(messagesPersistenceMock);
        when(messagesPersistenceMock.getMessagesFrom(0)).thenReturn(messages);
        when(messagesPersistenceMock.getNextSeq()).thenReturn(5);
        Messages response = getMessages(0);
        assertThat(response.getMessages()).isEqualTo(messages);
    }

    @Test
    public void getMessagesFromCorrectlyIndex() throws Exception{
        Messages response =  getMessages(1);
        verify(messagesPersistenceMock).getMessagesFrom(1);
    }
    @Test
    public void getMessagesFromNotNegativeIndex() throws Exception{
        Messages response =  getMessages(-1);
        verify(messagesPersistenceMock).getMessagesFrom(0);
    }

    private Messages getMessages(int index) {
        WebResource webResource = client().resource(Constants.API_SERVER_PATH).queryParam(Constants.GET_PARAM_NEXT_SEQ,Integer.toString(index));
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);
        return builder.get(Messages.class);
    }

    private ClientResponse postMessage(Message messageToSend) {
        WebResource webResource = client().resource(Constants.API_SERVER_PATH);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);
        return builder.post(ClientResponse.class, messageToSend);
    }


}