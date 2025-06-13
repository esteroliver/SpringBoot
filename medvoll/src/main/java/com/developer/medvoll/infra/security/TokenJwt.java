package com.developer.medvoll.infra.security;

import jakarta.validation.constraints.NotBlank;

public record TokenJwt(@NotBlank String token,
                       @NotBlank String user
) { }
