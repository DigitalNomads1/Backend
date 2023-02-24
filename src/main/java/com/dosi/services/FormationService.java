package com.dosi.services;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
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
        Formation formation = repository.findById(id_formation).get();

        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation(id_formation)
                .codeUe(id_ue)
                .build();

        UniteEnseignement uniteEnseignement = uniteEnseignementRepository.findById(uniteEnseignementId).get();

        return  elementConstitutifRepository.findByCodeUE(uniteEnseignement);  }
}
