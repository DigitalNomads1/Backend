package com.dosi.services;

import com.dosi.entities.Etudiant;
<<<<<<< HEAD
import com.dosi.exceptions.ApplicationException;
=======
import com.dosi.repositories.CandidatRepository;
import com.dosi.repositories.EnseignantRepository;
>>>>>>> eeb2d0b12b4d473db8437c0952b7bac79d4a896e
import com.dosi.repositories.EtudiantRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService extends BaseService<Etudiant, String> {


    public EtudiantService(EtudiantRepository etudiantRepository) {
        super(etudiantRepository);
    }
<<<<<<< HEAD
=======
    public Etudiant findByEmail(String email){
        return ((EtudiantRepository)repository).findByEmail(email);
    }
    public Etudiant findByEmailUbo(String email){
        return ((EtudiantRepository)repository).findByEmailUbo(email);
    }
>>>>>>> eeb2d0b12b4d473db8437c0952b7bac79d4a896e

    public Etudiant create(Etudiant etudiant) {
        if (etudiant.getId() != null) {
        if (repository.existsById(etudiant.getId())) {
                throw new EntityExistsException("Etudiant avec l'id " + etudiant.getId() + " existe déjà!");
            }
        }
        if (findByEmail(etudiant.getEmail()) != null) {
            throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");
        }
        if (findByEmailUbo(etudiant.getEmailUbo()) != null) {
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

<<<<<<< HEAD
    }
    @Override
    public Etudiant update(Etudiant etudiant) {
        Etudiant optionEtudiant = repository.findById(etudiant.getId()).orElse(null);

            if (optionEtudiant == null || !etudiant.getEmail().equals(optionEtudiant.getEmail()))
                if (etudiantRepository.findByEmail(etudiant.getEmail()) != null)
                    throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");


            if (optionEtudiant == null || !etudiant.getEmailUbo().equals(optionEtudiant.getEmailUbo()))
                if (etudiantRepository.findByEmailUbo(etudiant.getEmailUbo()) != null)
=======
            if (etudiant1 == null || !etudiant.getEmail().equals(etudiant1.getEmail()))
                if (findByEmail(etudiant.getEmail()) != null)
                    throw new EntityExistsException("Email personnel de l'etudiant existe déjà!");


            if (etudiant1 == null || !etudiant.getEmailUbo().equals(etudiant1.getEmailUbo()))
                if (findByEmailUbo(etudiant.getEmailUbo()) != null)
>>>>>>> eeb2d0b12b4d473db8437c0952b7bac79d4a896e
                    throw new EntityExistsException("Email Ubo de l'etudiant existe déjà!");

        return repository.save(etudiant);
    }

}

