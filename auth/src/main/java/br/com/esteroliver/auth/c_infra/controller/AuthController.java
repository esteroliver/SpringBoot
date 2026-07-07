package br.com.esteroliver.auth.c_infra.controller;

import br.com.esteroliver.auth.b_application.dto.TokenResultDTO;
import br.com.esteroliver.auth.c_infra.security.JwtTokenService;
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

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> autenticarUsuario(@RequestBody LoginDTO dto, HttpServletResponse response) {

        TokenResultDTO token = authService.autenticar(dto);
        ResponseCookie cookie = authService.gerarCookie(token.refreshToken());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        TokenResponseDTO responseDTO = new TokenResponseDTO(token.email(), token.papel(), token.token());

        return ResponseEntity.ok(responseDTO);

    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            HttpServletResponse response
    ){

        TokenResultDTO token = authService.refreshToken(refreshToken);
        ResponseCookie cookie = authService.gerarCookie(token.refreshToken());
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        TokenResponseDTO responseDTO = new TokenResponseDTO(token.email(), token.papel(), token.token());

        return ResponseEntity.ok(responseDTO);

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response){

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false) //deixar false para ambiente em http
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.noContent().build();

    }
}
