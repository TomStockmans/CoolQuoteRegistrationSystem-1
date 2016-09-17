package be.swsb.cqrs.conversation;

import be.swsb.cqrs.Application;
import be.swsb.cqrs.JerseyConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import static be.swsb.jaxrs.test.ResponseAssertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, JerseyConfig.class})
@WebIntegrationTest
public class ConversationResourceBaseTest {

    private ConversationResource conversationResource;

    @Before
    public void setUp() throws Exception {
        WebTarget baseTarget = JerseyClientBuilder.newBuilder().build().target("http://localhost:9000");
        conversationResource = WebResourceFactory.newResource(ConversationResource.class, baseTarget);
    }

    @Test
    public void getById_DoesStuff() throws Exception {
        String id = "test";
        Conversation conversation = conversationResource.get(id).readEntity(Conversation.class);

        assertThat(conversation.getId()).isNotEmpty();
    }

    @Test
    public void create_ValidConversation_ReturnsNewLocation() throws Exception {
        Response response = conversationResource.create(new Conversation());

        assertThat(response).hasStatus(Response.Status.CREATED);
//        assertThat(response).hasLocation(UriBuilder.fromResource(ConversationResourceBase.class).scheme("http").host("localhost").port(9000).path("1234").build());
        assertThat(response).hasLocation(UriBuilder.fromUri("http://localhost:9000/conversation/{id}").build("1234"));
    }
}