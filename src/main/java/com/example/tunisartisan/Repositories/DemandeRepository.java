package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    public Demande findByIdDemande(Long id);
}
