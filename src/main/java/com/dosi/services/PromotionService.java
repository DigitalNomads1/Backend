package com.dosi.services;

import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import com.dosi.repositories.FormationRepository;
import com.dosi.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService extends BaseService<Promotion, PromotionId>{

    public PromotionService(PromotionRepository promotionRepository){
        super(promotionRepository);
    }

    public void deleteByEmbeddedId(String annee,String formation)
    {
        ((PromotionRepository)repository).deleteByEmbeddedId(annee,formation);
    }

}
