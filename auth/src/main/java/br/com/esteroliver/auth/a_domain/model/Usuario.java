package br.com.esteroliver.auth.a_domain.model;

import br.com.esteroliver.auth.a_domain.enums.Papel;
import br.com.esteroliver.auth.b_application.dto.UsuarioPostDTO;
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

    private String senha;

    @Enumerated(EnumType.STRING)
    private Papel papel;

    public Usuario(UsuarioPostDTO dto){
        email = dto.email();
        nome = dto.nome();
        papel = dto.papel();
    }
}