package com.dosi.services;

import com.dosi.entities.Candidat;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.CandidatRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatService extends BaseService<Candidat, String> {
    @Autowired
    CandidatRepository candidatRepository;

    public CandidatService(CandidatRepository candidatRepository) {
        super(candidatRepository);
    }

    public Candidat create(Candidat candidat) {
        if (candidat.getId() != null) {
            if (repository.existsById(candidat.getId())) {
                throw new EntityExistsException("Etudiant avec l'id " + candidat.getId() + " existe déjà!");
            }
        }
        if (candidatRepository.findByEmail(candidat.getEmail()) != null) {
            throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");
        }
        return repository.save(candidat);
    }

    @Override
    public void delete(String id) {
        super.delete(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicationException("Veuillez vérifier les données enregistrées du candidat.");
        }
    }
}