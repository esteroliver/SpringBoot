package br.com.esteroliver.auth.b_application.dto;

import br.com.esteroliver.auth.a_domain.enums.Papel;

public record UsuarioJwtDTO(

    Long id,

    String email,

    String nome,

    Papel papel,

    String token
    
) {
} 
