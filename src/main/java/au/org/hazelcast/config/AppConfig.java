package au.org.hazelcast.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by proy on 19/11/16.
 */
@Configuration
public class AppConfig {
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
