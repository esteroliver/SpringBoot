package br.com.esteroliver.auth.c_infra.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "3c86d3471ee61f25fa3e20ca1acadeff";
    private static final String ISSUER = "esteroliver";
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String gerarToken(UserDetailsImpl userDetails){
        try{
            return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(userDetails.getUsername())
            .withClaim("type", "access")
            .withIssuedAt(dataCriacao())
            .withExpiresAt(dataExpiracao())
            .sign(algorithm);
        }
        catch(JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String verificarToken(String token){
        try{
            return JWT.require(algorithm)
            .withIssuer(ISSUER)
            .withClaim("type", "access")
            .build()
            .verify(token)
            .getSubject();
        }
        catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    public String gerarRefreshToken(UserDetailsImpl userDetails){
        try{
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(userDetails.getUsername())
                    .withClaim("type", "refresh")
                    .withIssuedAt(dataCriacao())
                    .withExpiresAt(dataExpiracaoRefreshToken())
                    .sign(algorithm);
        }
        catch(JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar refresh token.", exception);
        }
    }

    public String validarRefreshToken(String token){
        try{
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim("type", "refresh")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            throw new JWTVerificationException("Refresh token inválido ou expirado.");
        }
    }

    private Instant dataCriacao(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant dataExpiracao(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(5).toInstant();
    }

    private Instant dataExpiracaoRefreshToken(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusDays(7).toInstant();
    }
}
