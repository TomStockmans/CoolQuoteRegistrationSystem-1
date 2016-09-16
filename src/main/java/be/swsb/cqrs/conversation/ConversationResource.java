package be.swsb.cqrs.conversation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path(ConversationResource.CONVERSATION_BASE_URL)
public interface ConversationResource {

    String CONVERSATION_BASE_URL = "conversation";

    @GET
    @Path("{id}")
    Conversation get(@PathParam("id") String id);
}
