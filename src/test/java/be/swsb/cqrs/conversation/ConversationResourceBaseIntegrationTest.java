package be.swsb.cqrs.conversation;

import be.swsb.cqrs.Application;
import be.swsb.cqrs.JerseyConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static be.swsb.cqrs.conversation.ConversationTestBuilder.aConversation;
import static be.swsb.cqrs.conversation.ConversationTestBuilder.aDefaultConversation;
import static be.swsb.jaxrs.test.ResponseAssertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, JerseyConfig.class})
@WebIntegrationTest
public class ConversationResourceBaseIntegrationTest {

    @Autowired
    private ConversationRepository repo;

    private ConversationResource conversationResource;

    @Before
    public void setUp() throws Exception {
        WebTarget baseTarget = JerseyClientBuilder.newBuilder().build().target("http://localhost:9000");
        conversationResource = WebResourceFactory.newResource(ConversationResource.class, baseTarget);
    }

    @Test
    public void getById_DoesStuff() throws Exception {
        Conversation savedConvo = repo.save(aDefaultConversation().build());
        String id = savedConvo.getId();
        Conversation conversation = conversationResource.get(id).readEntity(Conversation.class);

        assertThat(conversation.getId()).isEqualTo(id);
        assertThat(conversation.getPunchLine().getText()).isEqualTo("punch");
        assertThat(conversation.getLines()).extracting(Line::getText).containsOnly("context");
    }

    @Test
    public void create_ValidConversation_ReturnsNewLocation() throws Exception {
        Response response = conversationResource.create(aConversation().build());

        assertThat(response).hasStatus(Response.Status.CREATED);
        assertThat(response).hasLocationContaining("http://localhost:9000/conversation/");
//        assertThat(response).hasLocation(UriBuilder.fromResource(ConversationResourceBase.class).scheme("http").host("localhost").port(9000).path("1234").build());
    }
}