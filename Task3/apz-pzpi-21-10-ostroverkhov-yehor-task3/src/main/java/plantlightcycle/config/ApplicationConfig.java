package plantlightcycle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for application-wide beans.
 */
@Configuration
public class ApplicationConfig {
    /**
     * Creates a {@link RestTemplate} bean.
     *
     * <p>This bean is used to perform RESTful operations such as
     * GET, POST, PUT, DELETE, etc., against external services.
     *
     * @return a configured {@link RestTemplate} instance.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
