package br.com.esteroliver.auth_keycloak.exceptions;

public class KeycloakIntegrationException extends RuntimeException {
    public KeycloakIntegrationException(String message) {
        super(message);
    }
}
