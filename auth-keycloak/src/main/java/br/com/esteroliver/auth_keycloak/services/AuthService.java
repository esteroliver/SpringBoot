package br.com.esteroliver.auth_keycloak.services;

import br.com.esteroliver.auth_keycloak.dto.LoginRequestDTO;
import br.com.esteroliver.auth_keycloak.dto.LoginResponseDTO;
import br.com.esteroliver.auth_keycloak.dto.UsuarioRequestDTO;
import br.com.esteroliver.auth_keycloak.dto.UsuarioResponseDTO;
import br.com.esteroliver.auth_keycloak.entity.Usuario;
import br.com.esteroliver.auth_keycloak.entity.enums.Papel;
import br.com.esteroliver.auth_keycloak.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    KeycloakAdminService keycloakAdminService;
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO cadastrarUsuarioCliente(UsuarioRequestDTO request){
        if(usuarioRepository.findByEmail(request.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado.");
        }

        String keycloakId;

        try{
            keycloakId = keycloakAdminService.criarUsuario(request);
        }
        catch (Exception exc){
            throw new RuntimeException("Erro ao criar usuário no Keycloak: " + exc.getMessage());
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuario.setPapel(Papel.CLIENTE);
        usuario.setKeycloakId(keycloakId);

        return UsuarioResponseDTO.from(usuarioRepository.save(usuario));
    }

    public LoginResponseDTO login(LoginRequestDTO request){
        var tokens = keycloakAdminService.login(request);

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return new LoginResponseDTO(
                (String) tokens.get("access_token"),
                (String) tokens.get("refresh_token"),
                (Number) tokens.get("expires_in"),
                (String) tokens.get("token_type"),
                UsuarioResponseDTO.from(usuario)
        );
    }
}
