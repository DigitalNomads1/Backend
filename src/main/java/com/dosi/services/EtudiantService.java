package com.dosi.services;

import com.dosi.entities.Enseignant;
import com.dosi.entities.Etudiant;
import com.dosi.repositories.CandidatRepository;
import com.dosi.repositories.EtudiantRepository;
import com.dosi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService extends BaseService<Etudiant, String> {

    public EtudiantService(EtudiantRepository etudiantRepository) {
        super(etudiantRepository);
    };


}

