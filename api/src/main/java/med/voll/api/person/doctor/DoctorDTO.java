package med.voll.api.person.doctor;

import jakarta.validation.constraints.NotBlank;
import med.voll.api.utils.address.Address;

public record DoctorDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String telefone,
                        @NotBlank String crm, @NotBlank Address endereco, @NotBlank SpecialtyEnum especialidade) {
}
