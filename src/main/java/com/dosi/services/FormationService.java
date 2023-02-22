package com.dosi.services;

import com.dosi.entities.Formation;
import com.dosi.repositories.FormationRepository;
import org.springframework.stereotype.Service;

@Service
public class FormationService extends BaseService<Formation, String>{
    public FormationService(FormationRepository formationRepository){
        super(formationRepository);
    }
}
