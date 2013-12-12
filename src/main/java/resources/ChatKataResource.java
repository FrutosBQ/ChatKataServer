package resources;

import configuration.Constants;
import core.Messages;
import core.Message;
import core.MessagesPersistence;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path(Constants.API_SERVER_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class ChatKataResource {

    private MessagesPersistence messagesPersistence;

    public ChatKataResource(MessagesPersistence messagesPersistence) {
        this.messagesPersistence = messagesPersistence;
    }

    @POST
    public Response addMessage(Message message) {
        if(message!=null){
            messagesPersistence.add(message);
            return Response.status(Response.Status.OK).entity(null).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
        }
    }

    @GET
    public Messages getMessages(@QueryParam(Constants.GET_PARAM_NEXT_SEQ) int index){
        if(index<0) index=0;
        List<Message> messages = messagesPersistence.getMessagesFrom(index);
        int nextSeq = messagesPersistence.getNextSeq();
        Messages response = new Messages(nextSeq,messages );
        return response;
    }
}
