package com.developer.medvoll.person.pacient;

import com.developer.medvoll.utils.entities.Address;
import com.developer.medvoll.utils.enums.GenderEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "pacients")
@Table(name = "pacients", schema = "person")
public class Pacient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private GenderEnum genero;

    @Embedded
    private Address endereco;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(columnDefinition = "json", name = "cid")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> cid;

    @Column(columnDefinition = "json", name = "alergia")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> alergia;

    public Pacient(){}

    public Pacient(PacientDto pacientDto){
        this.nome = pacientDto.nome();
        this.cpf = pacientDto.cpf();
        this.dataNascimento = pacientDto.dataNascimento();
        this.genero = pacientDto.genero();
        this.endereco = pacientDto.endereco();
        this.email = pacientDto.email();
        this.cid = pacientDto.cid();
        this.alergia = pacientDto.alergia();
        this.ativo = true;
    }
}
