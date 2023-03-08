package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.RubriqueEvaluationRepository;
import org.springframework.stereotype.Service;

@Service
public class RubriqueEvaluationService extends BaseService<RubriqueEvaluation, Integer> {

    public RubriqueEvaluationService(RubriqueEvaluationRepository rubriqueEvaluationRepository) {
        super(rubriqueEvaluationRepository);
    }

}
