package br.com.esteroliver.auth.b_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import br.com.esteroliver.auth.a_domain.model.UserDetailsImpl;
import br.com.esteroliver.auth.a_domain.model.Usuario;
import br.com.esteroliver.auth.a_domain.repository.UsuarioRepository;
import br.com.esteroliver.auth.b_application.dto.LoginDTO;
import br.com.esteroliver.auth.b_application.dto.TokenResponseDTO;
import br.com.esteroliver.auth.c_infra.security.JwtTokenService;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;

    private TokenResponseDTO autenticar(LoginDTO dto){

        UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
            dto.email(), dto.senha()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return new TokenResponseDTO().tokenResponse(
            jwtTokenService.gerarToken(userDetails), 
            usuario
        );
    }
    
}
