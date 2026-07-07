package br.com.esteroliver.auth.c_infra.config;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedException(Exception exception, HttpServletRequest request){
        LOGGER.error("Unhandled exception on {} ({}): {}", request.getMethod(), request.getRequestURI(), exception.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor."
        );

        problemDetail.setTitle("Unhandled exception");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request){
        LOGGER.error("Bad credentials exception on {} ({}): {}", request.getMethod(), request.getRequestURI(), exception.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED,
                "Credenciais inválidas ou incorretas."
        );

        problemDetail.setTitle("Bad credentials exception");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(JWTCreationException.class)
    public ProblemDetail handleJWTCreationException(JWTCreationException exception, HttpServletRequest request){
        LOGGER.error("JWT creation exception on {} ({}): {}", request.getMethod(), request.getRequestURI(), exception.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro ao criar o token JWT"
        );

        problemDetail.setTitle("JWT creation exception");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ProblemDetail handleJWTVerificationException(JWTVerificationException exception, HttpServletRequest request){
        LOGGER.error("JWT verification exception on {} ({}): {}", request.getMethod(), request.getRequestURI(), exception.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro ao verificar o token JWT"
        );

        problemDetail.setTitle("JWT verification exception");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ProblemDetail handleAuthorizationDeniedException(AuthorizationDeniedException exception, HttpServletRequest request){
        LOGGER.error("Authorization denied exception on {} ({}): {}", request.getMethod(), request.getRequestURI(), exception.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED,
                "Autorização negada"
        );

        problemDetail.setTitle("Authorization denied exception");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }
}
