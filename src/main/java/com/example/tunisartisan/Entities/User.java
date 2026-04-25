package com.example.tunisartisan.Entities;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Iduser;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private String tel;

    @OneToMany(mappedBy = "client")
    private Set<Demande> demandes;

    @OneToMany(mappedBy = "author")
    private Set<Avis> avis;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions;

}
