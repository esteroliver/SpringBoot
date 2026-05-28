package br.com.esteroliver.auth_keycloak.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        String senha
) {
}
