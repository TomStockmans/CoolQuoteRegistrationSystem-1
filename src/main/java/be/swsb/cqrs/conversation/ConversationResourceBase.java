package be.swsb.cqrs.conversation;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Resource
@Produces(APPLICATION_JSON)
@Path(ConversationResource.CONVERSATION_BASE_URL)
public class ConversationResourceBase implements ConversationResource {

    @GET
    @Path("{id}")
    @Override
    public Response get(@PathParam("id") String id) {
        Conversation conversation = new Conversation();
        conversation.setId("blablaID");
        return Response.ok(conversation).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Override
    public Response create(Conversation newConversation) {
        String newUuid = "1234";
        URI uri = UriBuilder.fromResource(ConversationResourceBase.class).path(newUuid).build();
        return Response.created(uri).build();
    }
}
