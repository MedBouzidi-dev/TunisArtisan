package com.example.tunisartisan.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "demandes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddemande;
    private String description;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    @ManyToOne
    private User client;

    @ManyToOne
    private Artisan artisan;

    @ManyToOne
    private Service service;
}
