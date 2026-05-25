package br.com.esteroliver.auth_keycloak.dto;


import br.com.esteroliver.auth_keycloak.entity.enums.Papel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String nome,
        @NotNull
        Papel papel,
        @NotBlank
        String senha
) {
}
