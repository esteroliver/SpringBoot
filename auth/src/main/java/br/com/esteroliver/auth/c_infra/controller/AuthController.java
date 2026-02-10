package br.com.esteroliver.auth.c_infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esteroliver.auth.b_application.dto.LoginDTO;
import br.com.esteroliver.auth.b_application.dto.TokenResponseDTO;
import br.com.esteroliver.auth.b_application.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> autenticarUsuario(@RequestBody LoginDTO dto) {
        TokenResponseDTO token = authService.autenticar(dto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    
}
