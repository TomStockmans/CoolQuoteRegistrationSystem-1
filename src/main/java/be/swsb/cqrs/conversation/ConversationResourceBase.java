package be.swsb.cqrs.conversation;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

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
        return Response.ok(repo.findAll()).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Override
    public Response create(Conversation newConversation) {
        if (!conversationValidator.validate(newConversation)) {
            return Response.status(Response.Status.BAD_REQUEST).header("Application-Error", "The conversation you tried to create is invalid").build();
        }
        Conversation conv = repo.save(newConversation);
        URI uri = UriBuilder.fromResource(ConversationResourceBase.class).path(conv.getId()).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response get(@PathParam("id") String id) {
        Conversation conversation = repo.findOne(id);
        if (conversation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(conversation).build();
    }

    @GET
    @Path("find")
    @Override
    public Response find(@QueryParam("participant") String participant, @QueryParam("victim") String victim) {
        List<Conversation> result = repo.findAll();
        if(participant != null) {
            result.removeIf(conversation -> !conversation.getLines().stream()
                                                                    .flatMap(line -> line.getParticipants().stream())
                                                                    .anyMatch(p -> p.getName().equalsIgnoreCase(participant)));
        }

        if(victim != null) {
            result.removeIf(conversation -> !conversation.getLines().stream()
                                                                    .flatMap(line -> line.getParticipants().stream())
                                                                    .anyMatch(p -> p.getName().equalsIgnoreCase(victim) && p.isVictim()));
        }

        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@PathParam("id") String id) {
        if (repo.findOne(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repo.delete(id);
        return Response.ok().build();
    }

    @PUT //should probably be POST instead of PUT because PUT is supposed to be idempotent
    @Consumes(APPLICATION_JSON)
    @Path("{id}")
    @Override
    public Response update(@PathParam("id") String id, Conversation updatedConversation) {
        if (repo.findOne(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repo.save(updatedConversation);
        return Response.ok(updatedConversation).build();
    }

}
