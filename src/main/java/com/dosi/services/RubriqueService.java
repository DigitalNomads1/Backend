package com.dosi.services;

import com.dosi.entities.Rubrique;
import com.dosi.repositories.RubriqueRepository;
import org.springframework.stereotype.Service;

@Service
public class RubriqueService extends BaseService<Rubrique, Integer> {

    public RubriqueService(RubriqueRepository rubriqueRepository) {
        super(rubriqueRepository);
    }


    public Rubrique getRubriqueByDesignation(String designation) {
        return ((RubriqueRepository)repository).findByDesignation(designation);
    }
}
