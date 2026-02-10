package br.com.esteroliver.auth.b_application.service;

import br.com.esteroliver.auth.a_domain.model.UserDetailsImpl;
import br.com.esteroliver.auth.a_domain.model.Usuario;
import br.com.esteroliver.auth.a_domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
                
        return new UserDetailsImpl(usuario);
    }
}
