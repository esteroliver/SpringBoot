package br.com.esteroliver.auth_keycloak.dto;

public record KeycloakCredentialDTO(
        String type,
        String value,
        boolean temporary
) {
}
