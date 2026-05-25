package br.com.esteroliver.auth_keycloak.dto;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken,
        Number expiresIn,
        String tokenType,
        UsuarioResponseDTO usuario
) {
}
