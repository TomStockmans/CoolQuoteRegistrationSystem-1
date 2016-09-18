package be.swsb.cqrs;

import be.swsb.cqrs.conversation.ConversationResourceBase;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ConversationResourceBase.class);
    }
}
