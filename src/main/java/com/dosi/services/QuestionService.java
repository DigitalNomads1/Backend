package com.dosi.services;

import com.dosi.entities.Question;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.repositories.QuestionRepository;
import com.dosi.repositories.ReponseEvaluationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService extends BaseService<Question, Integer> {

    public QuestionService(QuestionRepository questionRepository) {
        super(questionRepository);
    }

    public List<Map> getAllAvgOfQuestions(Integer idEval) {
        System.out.println(((QuestionRepository)repository).findAvgOfEveryQuestion(idEval));
        List<Map> list = new ArrayList<Map>();
        ((QuestionRepository)repository).findAvgOfEveryQuestion(idEval).forEach( question ->
                {
                    Map map = new HashMap();
                    map.put("id_question", question.get(1));
                    map.put("moyenne", question.get(2));
                    list.add(map);
                }
                );
        return list;
    }

}
