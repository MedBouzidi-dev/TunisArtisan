package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Demande;
import java.util.List;

public interface IDemandeService {
    Demande saveDemande(Demande demande);
    Demande getDemandeById(Long id);
    List<Demande> getAllDemandes();
    Demande updateDemande(Long id, Demande demande);
    void deleteDemande(Long id);
}

