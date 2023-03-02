package com.dosi.services;

import com.dosi.entities.Enseignant;
import com.dosi.entities.Etudiant;
import com.dosi.repositories.CandidatRepository;
import com.dosi.repositories.EtudiantRepository;
import com.dosi.services.BaseService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService extends BaseService<Etudiant, String> {
    @Autowired
    EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        super(etudiantRepository);
    }



    public Etudiant create(Etudiant etudiant) {
        if (etudiant.getId() != null) {
            if (repository.existsById(etudiant.getId())) {
                throw new EntityExistsException("Etudiant avec l'id " + etudiant.getId() + " existe déjà!");
            }
        }
        if (etudiantRepository.findByEmail(etudiant.getEmail()) != null) {
            throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");
        }
        if (etudiantRepository.findByEmailUbo(etudiant.getEmailUbo()) != null) {
            throw new EntityExistsException("Email Ubo de l'etudiant existe déjà!");
        }

        return repository.save(etudiant);
    }

    public Etudiant update(Etudiant etudiant) {
        Etudiant etudiant1 = repository.findById(etudiant.getId()).orElse(null);

            if (etudiant1 == null || !etudiant.getEmail().equals(etudiant1.getEmail()))
                if (etudiantRepository.findByEmail(etudiant.getEmail()) != null)
                    throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");


            if (etudiant1 == null || !etudiant.getEmailUbo().equals(etudiant1.getEmailUbo()))
                if (etudiantRepository.findByEmailUbo(etudiant.getEmailUbo()) != null)
                    throw new EntityExistsException("Email Ubo de l'etudiant existe déjà!");

        return repository.save(etudiant);
    }

}

