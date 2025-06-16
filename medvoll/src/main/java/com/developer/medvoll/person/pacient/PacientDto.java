package com.developer.medvoll.person.pacient;

import com.developer.medvoll.utils.entities.Address;
import com.developer.medvoll.utils.enums.GenderEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record PacientDto(@NotBlank String nome,
                         @NotBlank String cpf,
                         @NotNull Date dataNascimento,
                         @NotNull GenderEnum genero,
                         @NotNull @Valid Address endereco,
                         @NotBlank @Email String email,
                         List<String> cid,
                         List<String> alergia
                         ) { }
