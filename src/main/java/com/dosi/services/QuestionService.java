package com.dosi.services;

import com.dosi.entities.Question;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.repositories.QuestionRepository;
import com.dosi.repositories.ReponseEvaluationRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends BaseService<Question, Integer> {

    public QuestionService(QuestionRepository questionRepository) {
        super(questionRepository);
    }

}
