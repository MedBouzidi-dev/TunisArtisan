package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Avis;
import java.util.List;

public interface IAvisService {
    Avis saveAvis(Avis avis);
    Avis getAvisById(Long id);
    List<Avis> getAllAvis();
    Avis updateAvis(Long id, Avis avis);
    void deleteAvis(Long id);
}

