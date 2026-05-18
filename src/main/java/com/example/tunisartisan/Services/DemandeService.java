package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Demande;
import com.example.tunisartisan.Repositories.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService implements IDemandeService {
    @Autowired
    private DemandeRepository demandeRepository;

    @Override
    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    @Override
    public Demande getDemandeById(Long id) {
        Optional<Demande> demande = demandeRepository.findById(id);
        return demande.orElse(null);
    }

    @Override
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @Override
    public Demande updateDemande(Long id, Demande demande) {
        if (demandeRepository.existsById(id)) {
            demande.setIddemande(id);
            return demandeRepository.save(demande);
        }
        return null;
    }

    @Override
    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }
}

