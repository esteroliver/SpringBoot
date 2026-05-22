package br.com.esteroliver.auth.b_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.esteroliver.auth.c_infra.security.UserDetailsImpl;
import br.com.esteroliver.auth.b_application.dto.LoginDTO;
import br.com.esteroliver.auth.b_application.dto.TokenResponseDTO;
import br.com.esteroliver.auth.c_infra.security.JwtTokenService;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;

    public TokenResponseDTO autenticar(LoginDTO dto){

        UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
            dto.email(), dto.senha()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        assert userDetails != null;

        return TokenResponseDTO.tokenResponse(
            jwtTokenService.gerarToken(userDetails), 
            userDetails
        );
    }
    
}
