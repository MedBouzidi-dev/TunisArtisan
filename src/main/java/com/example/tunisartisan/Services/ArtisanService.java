package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Artisan;
import com.example.tunisartisan.Repositories.ArtisanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtisanService implements IArtisanService {
    @Autowired
    private ArtisanRepository artisanRepository;

    @Override
    public com.example.tunisartisan.Entities.Artisan addArtisan(com.example.tunisartisan.Entities.Artisan c) {
        return artisanRepository.save(c);
    }

    @Override
    public List<com.example.tunisartisan.Entities.Artisan> findAll() {
        return artisanRepository.findAll();
    }

    @Override
    public com.example.tunisartisan.Entities.Artisan findByIdEtudiant(Long id) {
        return artisanRepository.findByIdartisan(id);
    }

    @Override
    public com.example.tunisartisan.Entities.Artisan updateEtudiant(Long id, com.example.tunisartisan.Entities.Artisan c) {
        if (artisanRepository.existsById(id)) {
            c.setIdartisan(id);
            return artisanRepository.save(c);
        }
        return null;
    }

    @Override
    public void deleteEtudiant(Long id) {
        artisanRepository.deleteById(id);
    }
}

