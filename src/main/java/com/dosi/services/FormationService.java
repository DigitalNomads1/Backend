package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.ElementConstitutifRepository;
import com.dosi.repositories.FormationRepository;

import com.dosi.repositories.UniteEnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationService extends BaseService<Formation, String>{
    public FormationService(FormationRepository formationRepository){

        super(formationRepository);

    }

    @Autowired
    UniteEnseignementRepository uniteEnseignementRepository;
    public List<UniteEnseignement> findUEList(String id){
        Formation formation = repository.findById(id).get();
        return uniteEnseignementRepository.findByCodeFormation(formation) ;  }

    @Autowired
    ElementConstitutifRepository elementConstitutifRepository;
    public List<ElementConstitutif> findECList(String id_formation, String id_ue){

        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation(id_formation)
                .codeUe(id_ue)
                .build();

        UniteEnseignement uniteEnseignement = uniteEnseignementRepository.findById(uniteEnseignementId).get();

        return  elementConstitutifRepository.findByCodeUE(uniteEnseignement);  }

    @Override
    public void delete(String id) {
        Formation formation = repository.findById(id).get();
        List<UniteEnseignement> uniteEnseignements = uniteEnseignementRepository.findByCodeFormation(formation);
      if(uniteEnseignements.size() > 0)
          throw new ApplicationException("Veuillez vérifier les données enregistrées, Vérifier que la formation ne contient pas d'UE.");
        repository.deleteById(id);
    }
}
