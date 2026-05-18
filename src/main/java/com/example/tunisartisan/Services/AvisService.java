package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Avis;
import com.example.tunisartisan.Repositories.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisService implements IAvisService {
    @Autowired
    private AvisRepository avisRepository;

    @Override
    public Avis saveAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public Avis getAvisById(Long id) {
        Optional<Avis> avis = avisRepository.findById(id);
        return avis.orElse(null);
    }

    @Override
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    @Override
    public Avis updateAvis(Long id, Avis avis) {
        if (avisRepository.existsById(id)) {
            avis.setIdavis(id);
            return avisRepository.save(avis);
        }
        return null;
    }

    @Override
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }
}

