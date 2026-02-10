package br.com.esteroliver.auth.b_application.dto;

import br.com.esteroliver.auth.a_domain.enums.Papel;
import br.com.esteroliver.auth.a_domain.model.Usuario;

public record TokenResponseDTO(

    Long id,

    String email,

    String nome,

    Papel papel,

    String token
    
) {
    public static TokenResponseDTO tokenResponse(String token, Usuario usuario){

        return new TokenResponseDTO(
                    usuario.getId(), 
                    usuario.getEmail(), 
                    usuario.getNome(), 
                    usuario.getPapel(), 
                    token
                );
                
    } 
} 
