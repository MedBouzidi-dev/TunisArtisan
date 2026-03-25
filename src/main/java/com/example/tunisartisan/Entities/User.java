package com.example.tunisartisan.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Iduser;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private String tel;

}
