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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, JerseyConfig.class})
@WebIntegrationTest
public class ConversationResourceBaseTest {

    private WebTarget baseTarget;

    @Before
    public void setUp() throws Exception {
        baseTarget = JerseyClientBuilder.newBuilder().build().target("http://localhost:9000");
    }

    @Test
    public void getById_DoesStuff() throws Exception {
        String id = "test";
        Conversation conversation = WebResourceFactory.newResource(ConversationResource.class, baseTarget).get(id);

        assertThat(conversation.getId()).isNotEmpty();
    }
}