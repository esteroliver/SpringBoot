package br.com.esteroliver.auth_keycloak.entity;

import br.com.esteroliver.auth_keycloak.entity.enums.Papel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "usuario", schema = "public")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(name = "keycloak_id", nullable = false, unique = true)
    private String keycloakId;

    @Enumerated(EnumType.STRING)
    private Papel papel;
}
