package com.example.tunisartisan.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "avis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Avis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idavis;
    private String commentaire;
    private int note;

    @ManyToOne
    private Artisan artisan;

    @ManyToOne
    private User author;
}
