package br.com.esteroliver.auth_keycloak.controllers;

import br.com.esteroliver.auth_keycloak.dto.LoginRequestDTO;
import br.com.esteroliver.auth_keycloak.dto.LoginResponseDTO;
import br.com.esteroliver.auth_keycloak.dto.UsuarioRequestDTO;
import br.com.esteroliver.auth_keycloak.dto.UsuarioResponseDTO;
import br.com.esteroliver.auth_keycloak.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/cadastrar/cliente")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuarioCliente(@Valid @RequestBody UsuarioRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.cadastrarUsuarioCliente(request));
    }
}
