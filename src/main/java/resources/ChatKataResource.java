package resources;

import com.google.common.base.Optional;
import configuration.Constants;
import core.Message;
import core.Messages;
import core.MessagesPersistence;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path(Constants.API_SERVER_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public Messages getMessages(@QueryParam(Constants.GET_PARAM_NEXT_SEQ) Optional<String> param) {
        String indexString = param.or("0");
        int index = 0;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
        }
        List<Message> messages = messagesPersistence.getMessagesFrom(index);
        int nextSeq = messagesPersistence.getNextSeq();
        Messages response = new Messages(nextSeq,messages );
        return response;
    }
}
