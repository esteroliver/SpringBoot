package br.com.esteroliver.auth_keycloak.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String senha,
        @NotBlank
        @Email
        String email
) {
}
