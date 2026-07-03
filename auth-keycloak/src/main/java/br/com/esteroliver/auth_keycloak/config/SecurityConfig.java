package br.com.esteroliver.auth_keycloak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    KeycloakProperties keycloakProperties;

    private final String[] URLS_CORS_ENABLES = {
            "http://localhost:4200"
    };

    private final String[] ENDPOINTS_PUBLICOS = {
            "/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         return http
             .csrf(AbstractHttpConfigurer::disable)
             .sessionManagement((session) ->
                 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             )
             .authorizeHttpRequests((auth) -> auth
                     .requestMatchers(ENDPOINTS_PUBLICOS).permitAll()
                     .anyRequest().permitAll()
             )
             .oauth2ResourceServer((oauth2) ->
                     oauth2.jwt(jwt ->
                             jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                     )
             )
             .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if(resourceAccess == null)
                return List.of();

            var clientAccess = (Map<String, Object>) resourceAccess.get(keycloakProperties.getClientId());
            if (clientAccess == null)
                return List.of();

            var roles = (List<String>) clientAccess.get("roles");
            return roles.stream()
                    .map(role -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();
        });
        return converter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration conf = new CorsConfiguration();

        conf.setAllowedOrigins(List.of(URLS_CORS_ENABLES));
        conf.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        conf.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        conf.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);

        return source;
    }

}
