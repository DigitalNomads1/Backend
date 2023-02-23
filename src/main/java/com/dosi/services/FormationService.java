package com.dosi.services;

import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
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

}
