package com.developer.medvoll.person.pacient;

import com.developer.medvoll.utils.entities.AddressDto;
import com.developer.medvoll.utils.enums.GenderEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record PacientPutDto(@NotNull Long id,
                            @Valid AddressDto endereco,
                            GenderEnum genero,
                            String telefone,
                            @Email String email,
                            List<String> cid,
                            List<String> alergia
) { }
