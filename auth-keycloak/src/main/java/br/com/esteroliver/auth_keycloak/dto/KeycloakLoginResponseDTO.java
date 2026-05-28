package br.com.esteroliver.auth_keycloak.dto;

public record KeycloakLoginResponseDTO(
        String access_token,
        String refresh_token,
        Number expires_in,
        String token_type
) { }
