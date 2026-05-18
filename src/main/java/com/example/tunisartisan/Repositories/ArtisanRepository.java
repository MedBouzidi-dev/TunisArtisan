package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtisanRepository extends JpaRepository<Artisan,Long> {
    public Artisan findByIdartisan(Long id);
}
