package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.entities.AddressDto;
import com.developer.medvoll.utils.enums.SpeciltyEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorPostDto(@NotBlank String nome, @NotBlank @Email String email,
		@NotBlank @Pattern(regexp = "\\d{4,6}") String crm, @NotNull @Valid AddressDto endereco,
		@NotNull SpeciltyEnum especialidade) {
}
