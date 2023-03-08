package com.dosi.services;

import com.dosi.entities.Question;
import com.dosi.entities.QuestionEvaluation;
import com.dosi.repositories.QuestionEvaluationRepository;
import com.dosi.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionEvaluationService extends BaseService<QuestionEvaluation, Integer> {

    public QuestionEvaluationService(QuestionEvaluationRepository questionEvaluationRepository) {
        super(questionEvaluationRepository);
    }

}
