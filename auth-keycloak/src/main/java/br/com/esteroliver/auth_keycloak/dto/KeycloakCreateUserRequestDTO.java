package br.com.esteroliver.auth_keycloak.dto;

import java.util.List;

public record KeycloakCreateUserRequestDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        Boolean enabled,
        List<KeycloakCredential> credentials
) {
}
