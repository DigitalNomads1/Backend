package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.ElementConstitutifRepository;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ECService extends BaseService<ElementConstitutif, ElementConstitutifId> {
    public ECService(ElementConstitutifRepository repository) {
        super(repository);
    }


}