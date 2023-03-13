package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.RubriqueEvaluationRepository;
import com.dosi.repositories.RubriqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubriqueEvaluationService extends BaseService<RubriqueEvaluation, Integer> {

    @Autowired
    RubriqueRepository rubriqueRepository;
    public RubriqueEvaluationService(RubriqueEvaluationRepository rubriqueEvaluationRepository) {
        super(rubriqueEvaluationRepository);
    }
    @Override
    public RubriqueEvaluation create(RubriqueEvaluation entity) {
        System.out.println("SERVICE" + entity);
        Rubrique rubrique = rubriqueRepository.findById(entity.getIdRubrique().getId()).orElseThrow(() -> new EntityNotFoundException("Rubrique not found"));
        entity.setIdRubrique(rubrique);
        return super.create(entity);
    }
}
