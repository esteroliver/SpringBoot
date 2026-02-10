package br.com.esteroliver.auth.b_application.dto;

import br.com.esteroliver.auth.a_domain.enums.Papel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioPostDTO(

    @Email
    String email,

    @NotBlank
    String nome,

    @NotBlank
    String senha,

    @NotNull
    Papel papel
    
) {
}