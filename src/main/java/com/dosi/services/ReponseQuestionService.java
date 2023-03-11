package com.dosi.services;

import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import com.dosi.entities.ReponseQuestion;
import com.dosi.entities.ReponseQuestionId;
import com.dosi.repositories.PromotionRepository;
import com.dosi.repositories.ReponseQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class ReponseQuestionService extends BaseService<ReponseQuestion, ReponseQuestionId>{

    public ReponseQuestionService(ReponseQuestionRepository reponseQuestionRepository){
        super(reponseQuestionRepository);
    }
/*
    public void deleteByEmbeddedId(String annee,String formation)
    {
        ((PromotionRepository)repository).deleteByEmbeddedId(annee,formation);
    }
*/
}
