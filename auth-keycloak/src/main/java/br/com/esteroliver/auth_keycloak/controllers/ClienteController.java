package br.com.esteroliver.auth_keycloak.controllers;

import br.com.esteroliver.auth_keycloak.dto.UsuarioResponseDTO;
import br.com.esteroliver.auth_keycloak.entity.Usuario;
import br.com.esteroliver.auth_keycloak.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@PreAuthorize("hasRole('CLIENTE')")
public class ClienteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/me")
    public UsuarioResponseDTO me(JwtAuthenticationToken authToken){
        String keycloakId = authToken.getToken().getSubject();
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return UsuarioResponseDTO.from(usuario);
    }

    @GetMapping
    public String hello(){
        return "Hello World!";
    }

}
