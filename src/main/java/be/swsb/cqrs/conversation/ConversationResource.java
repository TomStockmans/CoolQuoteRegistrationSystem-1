package be.swsb.cqrs.conversation;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Path(ConversationResource.CONVERSATION_BASE_URL)
public interface ConversationResource {

    String CONVERSATION_BASE_URL = "conversation";

    @GET
    Response all();

    @GET
    @Path("{id}")
    Response get(@PathParam("id") String id);

    @POST
    @Consumes(APPLICATION_JSON)
    Response create(Conversation newConversation);

    @PUT
    @Consumes(APPLICATION_JSON)
    Response update(Conversation updatedConversation);
}
