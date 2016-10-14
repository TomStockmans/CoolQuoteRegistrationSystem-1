package be.swsb.cqrs;

import be.swsb.cqrs.conversation.ConversationResourceBase;
import be.swsb.cqrs.jersey.CORSFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ConversationResourceBase.class);
        register(CORSFilter.class);
//        register(LoggingFeature.class);
    }
}
