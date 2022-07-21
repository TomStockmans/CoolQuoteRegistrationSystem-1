package be.swsb.cqrs.conversation;

import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Resource
@Produces(APPLICATION_JSON)
@Path(ConversationResource.CONVERSATION_BASE_URL)
public class ConversationResourceBase implements ConversationResource {

    @Inject
    private ConversationRepository repo;

    @Inject
    private ConversationValidator conversationValidator;

    @Override
    @GET
    public Response all() {
        return Response.ok(repo.findAll(new Sort(Sort.Direction.DESC, "createdOn"))).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Override
    public Response create(Conversation newConversation) {
        if (!conversationValidator.validate(newConversation)) {
            return Response.status(Response.Status.BAD_REQUEST).header("Application-Error", "The conversation you tried to create is invalid").build();
        }
        Conversation conv = repo.save(newConversation);
        URI uri = UriBuilder.fromResource(ConversationResourceBase.class).path(String.valueOf(conv.getId())).build();
        return Response.created(uri).entity(conv).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response get(@PathParam("id") String id) {
        Conversation conversation = repo.findOne(Long.getLong(id));
        if (conversation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(conversation).build();
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@PathParam("id") String id) {
        if (repo.findOne(Long.getLong(id)) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repo.delete(Long.getLong(id));
        return Response.ok().build();
    }

    @PUT //should probably be POST instead of PUT because PUT is supposed to be idempotent
    @Consumes(APPLICATION_JSON)
    @Path("{id}")
    @Override
    public Response update(@PathParam("id") String id, Conversation updatedConversation) {
        if (repo.findOne(Long.getLong(id)) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repo.save(updatedConversation);
        return Response.ok(updatedConversation).build();
    }

}
