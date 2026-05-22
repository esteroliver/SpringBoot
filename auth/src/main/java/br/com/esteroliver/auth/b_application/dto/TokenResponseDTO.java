package br.com.esteroliver.auth.b_application.dto;

import br.com.esteroliver.auth.a_domain.enums.Papel;
import br.com.esteroliver.auth.c_infra.security.UserDetailsImpl;

public record TokenResponseDTO(
        String email,
        Papel papel,
        String token
) {
    public static TokenResponseDTO tokenResponse(String token, UserDetailsImpl usuario){

        return new TokenResponseDTO(
                    usuario.getUsername(),
                    usuario.getUsuario().getPapel(),
                    token
                );
                
    } 
} 
