package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.ElementConstitutifRepository;
import com.dosi.repositories.FormationRepository;

import com.dosi.repositories.UniteEnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<ElementConstitutif> findECList(String id_formation, String id_ue) {

        try {
            UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                    .codeFormation(id_formation)
                    .codeUe(id_ue)
                    .build();

            UniteEnseignement uniteEnseignement = uniteEnseignementRepository.findById(uniteEnseignementId).get();

            return elementConstitutifRepository.findByCodeUE(uniteEnseignement);
        } catch (Error e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"les information saisies sont incorrectes");

        }
    }
}
