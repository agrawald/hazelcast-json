package au.org.hazelcast.config;

import org.boon.json.JsonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by proy on 19/11/16.
 */
@Configuration
public class AppConfig {
    @Bean
    org.boon.json.ObjectMapper objectMapper() {
        return JsonFactory.create();
    }
}
