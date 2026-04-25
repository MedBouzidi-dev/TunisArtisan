package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis,Long> {
    public Avis findByIdAvis(Long id);
}
