package br.com.esteroliver.auth.b_application.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.esteroliver.auth.a_domain.model.Usuario;
import br.com.esteroliver.auth.a_domain.repository.UsuarioRepository;
import br.com.esteroliver.auth.b_application.dto.UsuarioPostDTO;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void criar(UsuarioPostDTO dto) throws BadRequestException{
        if(usuarioRepository.findByEmail(dto.email()).isPresent()){
            throw new BadRequestException("Já existe usuário com esse e-mail.");
        }
        
        Usuario usuario = new Usuario(dto);

        usuario.setSenha(
            passwordEncoder.encode(dto.senha())
        );

        usuarioRepository.save(usuario);
    }
    
}
