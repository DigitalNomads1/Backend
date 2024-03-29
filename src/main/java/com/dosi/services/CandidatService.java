package com.dosi.services;

import com.dosi.entities.Candidat;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.CandidatRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

 @Service
public class CandidatService extends BaseService<Candidat,String> {

     public CandidatService(CandidatRepository candidatRepository) {
         super(candidatRepository);
     }

     /**
      * @param email
      * @return Candidat
      */
     public Candidat findByEmail(String email){
         return ((CandidatRepository)repository).findByEmail(email);
     }

     /**
      * @param candidat
      * @return Candidat
      */
     public Candidat create(Candidat candidat) {
         if(candidat.getId() != null )
         {
             if (repository.existsById(candidat.getId())) {
                 throw new EntityExistsException("Le candidat avec l'id " + candidat.getId() + " existe déjà!");
             }
         }
         if(findByEmail(candidat.getEmail()) != null)
         {
             throw new EntityExistsException("Email personnel du candidat existe déjà!");
         }
         return repository.save(candidat);
     }

     /**
      * @param candidat
      * @return Candidat
      */
     @Override
     public Candidat update(Candidat candidat) {
         Candidat optionalCandidat = repository.findById(candidat.getId()).orElse(null);
         if (optionalCandidat == null || !candidat.getEmail().equals(optionalCandidat.getEmail()))
             if (((CandidatRepository)repository).findByEmail(candidat.getEmail()) != null)
                 throw new EntityExistsException("Email personnel du candidat existe déjà!");
         return repository.save(candidat);
     }

     /**
      * @param id
      */
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