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
         if(candidat.getId() != null )
         {
             if (repository.existsById(candidat.getId())) {
                 throw new EntityExistsException("Le candidat avec l'id " + candidat.getId() + " existe déjà!");
             }
         }
         if(candidatRepository.findByEmail(candidat.getEmail()) != null)
         {
             throw new EntityExistsException("Email personnel du candidat existe déjà!");
         }
         return repository.save(candidat);
     }

     @Override
     public Candidat update(Candidat candidat) {
         Candidat optionalCandidat = repository.findById(candidat.getId()).orElse(null);

         if (optionalCandidat == null || !candidat.getEmail().equals(optionalCandidat.getEmail()))
             if (candidatRepository.findByEmail(candidat.getEmail()) != null)
                 throw new EntityExistsException("Email personnel du candidat existe déjà!");
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