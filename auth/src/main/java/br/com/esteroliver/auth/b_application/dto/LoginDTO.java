package br.com.esteroliver.auth.b_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

    @Email
    String email,

    @NotBlank
    String senha
    
) {
}
