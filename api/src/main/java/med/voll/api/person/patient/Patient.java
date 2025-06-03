package med.voll.api.person.patient;

import lombok.Data;
import med.voll.api.person.doctor.SpecialtyEnum;
import med.voll.api.utils.address.Address;

@Data
public class Patient {
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Address endereco;
}
