package be.swsb.cqrs.conversation;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Resource
@Produces(MediaType.APPLICATION_JSON)
@Path(ConversationResource.CONVERSATION_BASE_URL)
public class ConversationResourceBase implements ConversationResource {

    @GET
    @Path("{id}")
    @Override
    public Conversation get(@PathParam("id") String id) {
        Conversation conversation = new Conversation();
        conversation.setId("blablaID");
        return conversation;
    }
}
