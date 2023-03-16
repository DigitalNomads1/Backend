package com.dosi.services;

import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UEService extends BaseService<UniteEnseignement, UniteEnseignementId> {

    @Autowired
    EnseignantRepository enseignantRepository;

    public UEService(UniteEnseignementRepository uniteEnseignementRepository) {
        super(uniteEnseignementRepository);
    }
    @Autowired
    UniteEnseignementRepository uniteEnseignementRepository;

    @Override
    public List<UniteEnseignement> findAll() {
        return super.findAll();
    }

    @Override
    public UniteEnseignement create(UniteEnseignement uniteEnseignement){
        if(uniteEnseignement.getId() != null ){
            if( repository.existsById((UniteEnseignementId)uniteEnseignement.getId())){
                throw new EntityExistsException("Entité" + uniteEnseignement+"existe déja");
            }
        }
        return  repository.save(uniteEnseignement);
    }

    @Override
    public void delete(UniteEnseignementId id) {
        int size = uniteEnseignementRepository.findById(id).get().getListeEC().size();
        System.out.println(size);
       if(uniteEnseignementRepository.findById(id).get().getListeEC().size() > 0){
              throw new ApplicationException("Veuillez vérifier les données enregistrées, Vérifier que l'UE n'a pas des EC.");
         }
          super.delete(id);
          try {
                repository.deleteById(id);
          } catch (Exception e) {
                throw new ApplicationException("Veuillez vérifier les données enregistrées, Vérifier que l'UE n'a pas des EC.");
          }
       }

    public String getNextAcademicYear(String currentAcademicYear) {
        String[] parts = currentAcademicYear.split("-");
        int year1 = Integer.parseInt(parts[0]);
        int year2 = Integer.parseInt(parts[1]);
        int nextYear1 = year1 + 1;
        int nextYear2 = year2 + 1;
        return nextYear1 + "-" + nextYear2;
    }

}
