package be.swsb.cqrs;

import be.swsb.cqrs.conversation.ConversationResourceBase;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ConversationResourceBase.class);
        register(JavaTimeModule.class);
//        register(LoggingFeature.class);
    }
}
