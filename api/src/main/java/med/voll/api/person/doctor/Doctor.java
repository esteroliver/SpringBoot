package med.voll.api.person.doctor;

import lombok.Data;
import med.voll.api.utils.address.Address;

@Data
public class Doctor {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Address endereco;
    private SpecialtyEnum especialidade;
}
