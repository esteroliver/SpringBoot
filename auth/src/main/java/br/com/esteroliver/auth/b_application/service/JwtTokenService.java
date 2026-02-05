package br.com.esteroliver.auth.b_application.service;

import br.com.esteroliver.auth.a_domain.model.UserDetailsImpl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;


@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "3c86d3471ee61f25fa3e20ca1acadeff";
    private static final String ISSUER = "esteroliver";

    public String gerarToken(UserDetailsImpl userDetails){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(userDetails.getUsername())
            .withIssuedAt(dataCriacao())
            .withExpiresAt(dataExpiracao())
            .sign(algorithm);
        }
        catch(JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    private Instant dataCriacao(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant dataExpiracao(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(5).toInstant();
    }
}
