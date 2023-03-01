package com.dosi.services;

import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.FormationRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UEService extends BaseService<UniteEnseignement, UniteEnseignementId> {

    public UEService(UniteEnseignementRepository repository) {
        super(repository);
    }

    public UniteEnseignement create(UniteEnseignement uniteEnseignement){
        if(uniteEnseignement.getId() != null ){
            if( repository.existsById((UniteEnseignementId)uniteEnseignement.getId())){
                throw new EntityExistsException("Entité" + uniteEnseignement+"existe déja");
            }
        }
        return  repository.save(uniteEnseignement);
    }

}
