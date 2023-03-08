package com.dosi.services;

import com.dosi.entities.ReponseEvaluation;
import com.dosi.repositories.ReponseEvaluationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReponseEvaluationService extends BaseService<ReponseEvaluation, Integer> {

    public ReponseEvaluationService(ReponseEvaluationRepository reponseEvaluationRepository) {
        super(reponseEvaluationRepository);
    }

}
