package com.example.tunisartisan.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "artisans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Artisan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idartisan;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatutArtisan statut;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "artisan")
    private Set<Service> services;

    @OneToMany(mappedBy = "artisan")
    private Set<Avis> avis;

    @OneToMany(mappedBy = "artisan")
    private Set<Demande> demandes;
}
