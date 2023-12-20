package de.srh.toolify.config;

import de.srh.toolify.filters.AccessTokenValidationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandlerBean() {
        return new ToolifySuccessAuthenticationHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandlerBean() {
        return new ToolifyFailureAuthenticationHandler();
    }

    @Bean
    public AccessTokenValidationFilter accessTokenValidationFilter(){
        return new AccessTokenValidationFilter();
    }
}