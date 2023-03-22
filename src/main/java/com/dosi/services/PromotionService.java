package com.dosi.services;

import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.PromotionRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionService extends BaseService<Promotion, PromotionId>{

    public PromotionService(PromotionRepository promotionRepository){
        super(promotionRepository);
    }

    @Override
    public void delete(PromotionId id) {
        System.out.println(id);
        System.out.println("INSIDE PROMOTION SEVRVICE");
        super.delete(id);
        try {
            ((PromotionRepository)repository).deletePromotionByCodeFormationAndAnneeUniversitaire(id.getCodeFormation(), id.getAnneeUniversitaire());
        } catch (Exception e) {
            throw new ApplicationException("Vous ne pouvez pas supprimer cette promotion car elle contient des évaluations, étudiants ou candidats!.");
        };
    }
}
