package com.developer.medvoll.person.doctor;

import com.developer.medvoll.utils.entities.Address;
import com.developer.medvoll.utils.enums.SpeciltyEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    private String crm;

    @Embedded
    private Address endereco;

    @Enumerated(EnumType.STRING)
    private SpeciltyEnum especialidade;

    public Doctor(){}

    public Doctor(DoctorPostDto doctorPostDto) {
        this.nome = doctorPostDto.nome();
        this.email = doctorPostDto.email();
        this.crm = doctorPostDto.crm();
        this.especialidade = doctorPostDto.especialidade();
        this.endereco = new Address(doctorPostDto.endereco());
    }
}
