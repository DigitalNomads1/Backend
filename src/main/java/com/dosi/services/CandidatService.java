package com.dosi.services;

import com.dosi.controllers.BaseController;
import com.dosi.entities.Candidat;
import com.dosi.entities.Etudiant;
import com.dosi.repositories.CandidatRepository;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.EtudiantRepository;
import com.dosi.services.BaseService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 @Service
public class CandidatService extends BaseService<Candidat,String> {

     public CandidatService(CandidatRepository candidatRepository) {
         super(candidatRepository);
     }

     public Candidat findByEmail(String email){
         return ((CandidatRepository)repository).findByEmail(email);
     }

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

     public Candidat update(Candidat candidat){
         Candidat candidat1=repository.findById(candidat.getId()).orElse(null);

         if (candidat1 == null || !candidat.getEmail().equals(candidat1.getEmail()))
             if(findByEmail(candidat.getEmail()) != null)
                throw new EntityExistsException("Email personnel du candidat existe déjà!");

         return repository.save(candidat);
     }
}