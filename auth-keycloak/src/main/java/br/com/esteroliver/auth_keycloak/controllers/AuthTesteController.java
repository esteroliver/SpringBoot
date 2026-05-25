package br.com.esteroliver.auth_keycloak.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth-teste")
public class AuthTesteController {

    @GetMapping
    public String hello(@AuthenticationPrincipal Jwt token){
        return "Hello world!";
    }

}
