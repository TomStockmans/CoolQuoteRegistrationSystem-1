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

    @POST
    @Consumes(APPLICATION_JSON)
    Response create(Conversation newConversation);

    @GET
    @Path("{id}")
    Response get(@PathParam("id") String id);

    @GET
    @Path("find")
    Response find(@QueryParam("participant") String participant, @QueryParam("victim") String victim);

    @DELETE
    @Path("{id}")
    Response delete(@PathParam("id") String id);

    @PUT //should probably be POST instead of PUT because PUT is supposed to be idempotent
    @Consumes(APPLICATION_JSON)
    @Path("{id}")
    Response update(@PathParam("id") String id, Conversation updatedConversation);
}
