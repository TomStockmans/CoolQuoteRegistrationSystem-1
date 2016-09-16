package be.swsb.cqrs.conversation;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Resource
@Produces(MediaType.APPLICATION_JSON)
@Path("/conversation")
public class ConversationResource {

    @GET
    @Path("{id}")
    public Conversation get(@PathParam("id") String id) {
        Conversation conversation = new Conversation();
        conversation.setId("blablaID");
        return conversation;
    }
}
