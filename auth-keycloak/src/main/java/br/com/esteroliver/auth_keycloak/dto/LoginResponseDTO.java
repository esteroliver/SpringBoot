package br.com.esteroliver.auth_keycloak.dto;

public record LoginResponseDTO(
        KeycloakLoginResponseDTO tokensInfos,
        UsuarioResponseDTO usuario
) {
}
