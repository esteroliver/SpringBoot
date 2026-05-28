package br.com.esteroliver.auth_keycloak.dto;

public record KeycloakCredential(
        String type,
        String value,
        boolean temporary
) {
}
