package com.dosi.services;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.ElementConstitutifId;
import com.dosi.repositories.ElementConstitutifRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import org.springframework.stereotype.Service;

@Service
public class ECService extends BaseService<ElementConstitutif, ElementConstitutifId> {
    public ECService(ElementConstitutifRepository repository) {
        super(repository);
    }

}