package com.developer.medvoll.utils.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(@NotBlank String logradouro,
                         @NotBlank String bairro,
                         @NotBlank String cidade,
                         @NotBlank String uf,
                         @NotBlank @Pattern(regexp = "\\d{8}") String cep,
                         String complemento,
                         String numero
) { }
