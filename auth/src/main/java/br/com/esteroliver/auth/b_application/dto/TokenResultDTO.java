package br.com.esteroliver.auth.b_application.dto;

import br.com.esteroliver.auth.a_domain.enums.Papel;

public record TokenResultDTO(
        String email,
        Papel papel,
        String token,
        String refreshToken
) {
}
