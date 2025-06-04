package med.voll.api.person.doctor;

import jakarta.persistence.*;

import lombok.Data;
import med.voll.api.utils.address.Address;

@Entity
@Data
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @ManyToOne
    private Address endereco;

    private SpecialtyEnum especialidade;
}
