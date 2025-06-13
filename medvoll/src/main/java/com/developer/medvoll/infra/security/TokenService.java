package com.developer.medvoll.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.developer.medvoll.person.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm alg = Algorithm.HMAC256(secret);
            return JWT.create()
                      .withIssuer("API Med Voll")
                      .withSubject(user.getLogin())
                      .withExpiresAt(expiretDate())
                      .sign(alg);
            } catch (RuntimeException e){
            throw new RuntimeException("Token generation failed.", e);
        }
    }

    private Instant expiretDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
