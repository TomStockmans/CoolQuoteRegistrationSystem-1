package be.swsb.cqrs;

import be.swsb.cqrs.conversation.ConversationResourceBase;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ConversationResourceBase.class);
    }
}
