package com.dosi.services;

import com.dosi.entities.ReponseEvaluation;
import com.dosi.entities.ReponseQuestion;
import com.dosi.repositories.ReponseEvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReponseEvaluationService extends BaseService<ReponseEvaluation, Integer> {

    public ReponseEvaluationService(ReponseEvaluationRepository reponseEvaluationRepository) {
        super(reponseEvaluationRepository);
    }

    @Override
    public ReponseEvaluation create(ReponseEvaluation entity) {

        System.out.println(entity);
        var reponseQuestionList = entity.getReponseQuestionList();
        entity.setReponseQuestionList(new ArrayList<>());
        var reponseEvaluation =  super.create(entity);
        for (ReponseQuestion reponseQuestion : reponseQuestionList)
        {
            reponseQuestion.setIdReponseEvaluation(reponseEvaluation);
            entity.getReponseQuestionList().add(reponseQuestion);
        }
         reponseEvaluation =  super.update(entity);
        return reponseEvaluation;
//        return reponseEvaluation;
    }
}
