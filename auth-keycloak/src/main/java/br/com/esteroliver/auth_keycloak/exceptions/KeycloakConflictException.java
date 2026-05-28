package br.com.esteroliver.auth_keycloak.exceptions;

public class KeycloakConflictException extends RuntimeException {
    public KeycloakConflictException(String message) {
        super(message);
    }
}
