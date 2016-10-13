package be.swsb.cqrs.conversation;

import be.swsb.cqrs.Application;
import be.swsb.cqrs.JerseyConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static be.swsb.cqrs.conversation.ConversationTestBuilder.*;
import static be.swsb.cqrs.conversation.LineTestBuilder.aSpeechLine;
import static be.swsb.jaxrs.test.ResponseAssertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class, JerseyConfig.class})
public class ConversationResourceBaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ConversationRepository repo;

    private ConversationResource conversationResource;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll(); //clean slate before every test run
        baseUrl = "http://localhost:" + port + "/api";
        WebTarget baseTarget = JerseyClientBuilder.newBuilder().build().target(baseUrl);
        conversationResource = WebResourceFactory.newResource(ConversationResource.class, baseTarget);
    }

    @Test
    public void get_WhenConversationPresent_ReturnsConversation() throws Exception {
        Conversation savedConvo = repo.save(aDefaultConversation().build());
        String id = savedConvo.getId();
        Conversation conversation = conversationResource.get(id).readEntity(Conversation.class);

        assertThat(conversation.getId()).isEqualTo(id);
        assertThat(conversation.getCreatedOn()).isEqualTo(CREATED_ON);
        assertThat(conversation.getConversationDate()).isEqualTo(CONVERSATION_DATE);
        assertThat(conversation.getLines()).extracting(Line::getText).containsOnly("context","punch");
        assertThat(conversation.getLines()).filteredOn(Line::isPunchLine).extracting(Line::getText).containsOnly("punch");
    }

    @Test
    public void get_NonExistingConversation_Returns404() throws Exception {
        repo.save(aDefaultConversation().build());
        Response response = conversationResource.get("someOtherId");

        assertThat(response).hasStatus(Response.Status.NOT_FOUND);
    }

    @Test
    public void all_WhenConversationsPresent_ReturnsAllConversations() throws Exception {
        Conversation snarf = aDefaultConversation().withPunchLine("Snarf snarf").build();
        Conversation liono = aDefaultConversation().withPunchLine("Thundercats! HOOooooooooo!").build();
        repo.save(Arrays.asList(snarf,liono));

        List<Conversation> conversations = Stream.of(conversationResource.all().readEntity(Conversation[].class)).collect(Collectors.toList());

        assertThat(conversations).containsOnly(snarf, liono);
    }

    @Test
    public void all_WhenNoConversationsPresent_Returns200() throws Exception {
        Response response = conversationResource.all();

        assertThat(response).hasStatus(Response.Status.OK);
    }

    @Test
    public void create_ValidConversation_ReturnsNewLocation() throws Exception {
        Conversation conversation = aDefaultConversation().withId(null).build();
        Response response = conversationResource.create(conversation);

        System.out.println("Response: "+response);
        assertThat(response).hasStatus(Response.Status.CREATED);
        assertThat(response).hasLocationContaining(baseUrl+"/conversation/");
    }

    @Test
    public void create_InvalidConversation_ReturnsBadRequest() throws Exception {
        Line speechLineWithoutParticipant = aSpeechLine().withoutParticipants().withText("derp").build();
        Conversation conversation = aDefaultConversation().withId(null).withLines(speechLineWithoutParticipant).build();
        Response response = conversationResource.create(conversation);

        assertThat(response.getHeaderString("Application-Error")).isEqualTo("The conversation you tried to create is invalid");
        assertThat(response).hasStatus(Response.Status.BAD_REQUEST);
    }

    @Test
    public void delete_WhenConversationPresent_DeletesConversation() throws Exception {
        Conversation savedConvo = repo.save(aDefaultConversation().build());
        String id = savedConvo.getId();
        Response response = conversationResource.delete(id);

        assertThat(response).hasStatus(Response.Status.OK);

        Conversation conversation = repo.findOne(id);
        assertThat(conversation).isNull();
    }

    @Test
    public void delete_NonExistingConversation_Returns404() throws Exception {
        Conversation savedConvo = repo.save(aDefaultConversation().build());
        String id = savedConvo.getId();
        Response response = conversationResource.delete("someOtherId");

        assertThat(response).hasStatus(Response.Status.NOT_FOUND);

        Conversation conversation = repo.findOne(id);
        assertThat(conversation).isEqualTo(savedConvo);
    }

    @Test
    public void update_ValidConversation_PersistsUpdatedConversationAndReturnsIt() throws Exception {
        Conversation snarf = aDefaultConversation().withPunchLine("Snarf snarf").build();
        Conversation savedSnarf = repo.save(snarf);
        String savedSnarfId = savedSnarf.getId();

        Conversation updatedInGUISnarf = aDefaultConversation().withId(savedSnarfId).withPunchLine("ThunderCats! HOOooooooo!").build();

        Response response = conversationResource.update(savedSnarfId, updatedInGUISnarf);

        assertThat(response).hasStatus(Response.Status.OK);

        Conversation snarfAfterUpdate = repo.findOne(savedSnarfId);
        assertThat(snarfAfterUpdate).isEqualTo(updatedInGUISnarf);
    }

    @Test
    public void update_NonExistingConversation_Returns404() throws Exception {
        Conversation snarf = aDefaultConversation().withPunchLine("Snarf snarf").build();
        Conversation savedSnarf = repo.save(snarf);
        String savedSnarfId = savedSnarf.getId();

        Conversation updatedInGUISnarf = aDefaultConversation().withId(savedSnarfId).withPunchLine("ThunderCats! HOOooooooo!").build();

        Response response = conversationResource.update("someOtherId", updatedInGUISnarf);

        assertThat(response).hasStatus(Response.Status.NOT_FOUND);

        Conversation snarfAfterUpdate = repo.findOne(savedSnarfId);
        assertThat(snarfAfterUpdate).isEqualTo(snarf);
    }
}