package br.com.esteroliver.auth.c_infra.controller;

import br.com.esteroliver.auth.b_application.dto.TokenResultDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.esteroliver.auth.b_application.dto.LoginDTO;
import br.com.esteroliver.auth.b_application.dto.TokenResponseDTO;
import br.com.esteroliver.auth.b_application.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> autenticarUsuario(@RequestBody LoginDTO dto, HttpServletResponse response) {
        try {
            TokenResultDTO token = authService.autenticar(dto);
            ResponseCookie cookie = authService.gerarCookie(token.refreshToken());
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Erro ao autenticar: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken, HttpServletResponse response){
        //todo
    }
    
}
