package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Artisan;

import java.util.List;

public interface IArtisanService {
    public Artisan addArtisan(Artisan c);

    public List<Artisan> findAll();

    public Artisan findByIdEtudiant(Long id);

    public Artisan updateEtudiant(Long id, Artisan c);

    public void deleteEtudiant(Long id);
}