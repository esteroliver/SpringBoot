package com.developer.medvoll.person.user;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String login,
                      @NotBlank String senha) { }
