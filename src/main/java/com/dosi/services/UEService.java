package com.dosi.services;

import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.FormationRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UEService extends BaseService<UniteEnseignement, UniteEnseignementId> {

    public UEService(UniteEnseignementRepository repository, FormationRepository formationRepository) {
        super(repository);
    }


    // add methods to implement business logic using the repository
}
