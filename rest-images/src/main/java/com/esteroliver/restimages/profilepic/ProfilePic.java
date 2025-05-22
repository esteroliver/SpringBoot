package com.esteroliver.restimages.profilepic;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "profilepics")
public class ProfilePic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "image")
    private byte[] image;
}
