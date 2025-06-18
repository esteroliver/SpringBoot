package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.entities.Address;
import com.developer.medvoll.utils.enums.SpeciltyEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "doctors")
@Table(name = "doctors", schema = "person")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "crm")
    private String crm;

    @Embedded
    private Address endereco;

    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private SpeciltyEnum especialidade;

    @Column(name = "ativo")
    private Boolean ativo;

    public Doctor(){}

    public Doctor(DoctorPostDto doctorPostDto) {
        this.nome = doctorPostDto.nome();
        this.cpf = doctorPostDto.cpf();
        this.email = doctorPostDto.email();
        this.crm = doctorPostDto.crm();
        this.especialidade = doctorPostDto.especialidade();
        this.endereco = new Address(doctorPostDto.endereco());
        this.ativo = true;
    }
}
