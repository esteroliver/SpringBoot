package com.developer.medvoll.person.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "usersapí")
@Table(name="usersapi")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String senha;

}
