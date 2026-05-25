package br.com.esteroliver.auth_keycloak.dto;

import br.com.esteroliver.auth_keycloak.entity.Usuario;
import br.com.esteroliver.auth_keycloak.entity.enums.Papel;

public record UsuarioResponseDTO(
        String email,
        String nome,
        Papel papel,
        String keycloakId
) {
    public static UsuarioResponseDTO from(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getPapel(),
                usuario.getKeycloakId()
        );
    }
}
