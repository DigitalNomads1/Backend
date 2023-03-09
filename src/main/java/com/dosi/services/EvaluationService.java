package com.dosi.services;

import com.dosi.entities.Etat;
import com.dosi.entities.Evaluation;
import com.dosi.entities.PromotionId;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService extends BaseService<Evaluation, Integer> {

    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    UniteEnseignementRepository uniteEnseignementRepository;

    @Autowired
    PromotionRepository promotionRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        super(evaluationRepository);
    }

    @Override
    public Evaluation create(Evaluation entity) {
        entity.setNoEnseignant(enseignantRepository.findById(entity.getNoEnseignant().getId()).get());
        entity.setUniteEnseignement(uniteEnseignementRepository.findById(entity.getUniteEnseignement().getId()).get());
        return super.create(entity);
    }

    public List<Evaluation> findEvaluationsNonPubliees() {
        return ((EvaluationRepository)repository).findByEtat(Etat.ELA.toString());
    }

    public List<Evaluation> findEvaluationsPubliees() {
        return ((EvaluationRepository)repository).findByEtat(Etat.DIS.toString());
    }

    // this method retrieve all Current Evaluations for this promotion
    public List<Evaluation> findEvaluationsByPromotion(String code_formation, String annee_universitaire) {
        var promotion = promotionRepository.findById(PromotionId.builder()
                        .codeFormation(code_formation)
                        .anneeUniversitaire(annee_universitaire)
                .build()).get();
//        return ((EvaluationRepository)repository).findByEtatAndPromotion(Etat.DIS.toString(),promotion);
          return ((EvaluationRepository)repository).findByPromotion(promotion);

    }
}
