package br.com.esteroliver.auth.a_domain.model;

import br.com.esteroliver.auth.a_domain.enums.Papel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Papel papel;
}