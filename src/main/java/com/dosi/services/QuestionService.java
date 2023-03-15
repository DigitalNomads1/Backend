package com.dosi.services;

import com.dosi.entities.Question;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.repositories.QuestionRepository;
import com.dosi.repositories.ReponseEvaluationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
public class QuestionService extends BaseService<Question, Integer> {

    public QuestionService(QuestionRepository questionRepository) {
        super(questionRepository);
    }

    public Map<Integer, Integer> getAllAvgOfQuestions( Integer idEval) {
        return ((QuestionRepository)repository).findAvgOfEveryQuestion(idEval);
    }

}
