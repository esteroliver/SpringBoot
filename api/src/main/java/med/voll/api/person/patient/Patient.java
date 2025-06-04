package med.voll.api.person.patient;

import jakarta.persistence.*;

import lombok.Data;
import med.voll.api.utils.address.Address;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    @ManyToOne
    private Address endereco;
}
