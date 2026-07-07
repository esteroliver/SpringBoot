package br.com.esteroliver.auth.b_application.service;

import br.com.esteroliver.auth.a_domain.model.Usuario;
import br.com.esteroliver.auth.a_domain.repository.UsuarioRepository;
import br.com.esteroliver.auth.b_application.dto.TokenResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.esteroliver.auth.b_application.dto.LoginDTO;
import br.com.esteroliver.auth.c_infra.security.JwtTokenService;

import java.time.Duration;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    public TokenResultDTO autenticar(LoginDTO dto){
        Usuario usuario = usuarioRepository.findByEmail(dto.email()).orElseThrow(
                () -> new BadCredentialsException("Usuário não encontrado.")
        );

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                    dto.email(), dto.senha()
            );
            authenticationManager.authenticate(usernamePasswordAuthToken);
        }
        catch (AuthenticationException exception){
            throw new BadCredentialsException("Credenciais incorretas.");
        }

        return new TokenResultDTO(
                usuario.getEmail(),
                usuario.getPapel(),
                jwtTokenService.gerarToken(usuario),
                jwtTokenService.gerarRefreshToken(usuario)
        );
    }

    public TokenResultDTO refreshToken(String token){
        if(token == null || token.isBlank()){
            throw new BadCredentialsException("Refresh token está vazio.");
        }

        String email = jwtTokenService.validarRefreshToken(token);
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new BadCredentialsException("Usuário não encontrado.")
        );

        return new TokenResultDTO(
                usuario.getEmail(),
                usuario.getPapel(),
                jwtTokenService.gerarToken(usuario),
                jwtTokenService.gerarRefreshToken(usuario)
        );
    }

    public ResponseCookie gerarCookie(String token){
        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(false) //deixar false para ambiente em http
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .build();
    }
    
}
