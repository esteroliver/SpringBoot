package com.developer.medvoll.auth;

import com.developer.medvoll.infra.security.TokenJwt;
import com.developer.medvoll.infra.security.TokenService;
import com.developer.medvoll.person.user.User;
import com.developer.medvoll.person.user.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDto user){
        var authToken = new UsernamePasswordAuthenticationToken(user.login(), user.senha());
        var auth = manager.authenticate(authToken);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        TokenJwt tokenResponse = new TokenJwt(token, user.login());
        return ResponseEntity.ok(tokenResponse);
    }
}
