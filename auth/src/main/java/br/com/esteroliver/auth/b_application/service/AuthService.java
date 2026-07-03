package br.com.esteroliver.auth.b_application.service;

import br.com.esteroliver.auth.b_application.dto.TokenResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.esteroliver.auth.c_infra.security.UserDetailsImpl;
import br.com.esteroliver.auth.b_application.dto.LoginDTO;
import br.com.esteroliver.auth.b_application.dto.TokenResponseDTO;
import br.com.esteroliver.auth.c_infra.security.JwtTokenService;

import java.time.Duration;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;

    public TokenResultDTO autenticar(LoginDTO dto){

        UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
            dto.email(), dto.senha()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        assert userDetails != null;

        return new TokenResultDTO(
                userDetails.getUsername(),
                userDetails.getUsuario().getPapel(),
                jwtTokenService.gerarToken(userDetails),
                jwtTokenService.gerarRefreshToken(userDetails)
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
