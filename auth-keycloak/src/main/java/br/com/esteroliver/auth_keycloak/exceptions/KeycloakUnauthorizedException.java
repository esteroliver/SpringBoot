package br.com.esteroliver.auth_keycloak.exceptions;

public class KeycloakUnauthorizedException extends RuntimeException {
    public KeycloakUnauthorizedException(String message) {
        super(message);
    }
}
