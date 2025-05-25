package auth.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import feign.RequestInterceptor;

@Configuration
public class FeignConfig {
    
    @Value("${services.user.secret}")
    private String serviceSecret;
    
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header(
            "X-Auth-Service-Secret", 
            serviceSecret
        );
    }
}