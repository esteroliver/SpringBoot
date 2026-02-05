package br.com.esteroliver.auth.c_infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    public static final String[] ENDPOINTS_PUBLICOS = {
        "/usuario/login",
        "/usuario/registro"
    };
    
}
