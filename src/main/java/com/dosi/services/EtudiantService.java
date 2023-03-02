package com.dosi.services;

import com.dosi.entities.Etudiant;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EtudiantRepository;
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

    @Override
    public void delete(String id) {
        super.delete(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicationException("Veuillez vérifier les données enregistrées, Vérifier que l'étudiant n'a pas des Evaluations.");
        }

    }
    @Override
    public Etudiant update(Etudiant etudiant) {
        Etudiant optionEtudiant = repository.findById(etudiant.getId()).orElse(null);

            if (optionEtudiant == null || !etudiant.getEmail().equals(optionEtudiant.getEmail()))
                if (etudiantRepository.findByEmail(etudiant.getEmail()) != null)
                    throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");


            if (optionEtudiant == null || !etudiant.getEmailUbo().equals(optionEtudiant.getEmailUbo()))
                if (etudiantRepository.findByEmailUbo(etudiant.getEmailUbo()) != null)
                    throw new EntityExistsException("Email Ubo de l'etudiant existe déjà!");

        return repository.save(etudiant);
    }

}

