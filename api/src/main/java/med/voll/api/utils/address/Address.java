package med.voll.api.utils.address;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String uf;

    private String complemento;
}
