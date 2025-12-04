package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.entities.AddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DoctorPutDto(@NotNull Long id, String nome, @Valid AddressDto endereco) {
}
