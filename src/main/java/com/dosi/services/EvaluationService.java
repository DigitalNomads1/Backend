package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
public class EvaluationService extends BaseService<Evaluation, Integer> {

    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    UniteEnseignementRepository uniteEnseignementRepository;
    @Autowired
    PromotionRepository promotionRepository;
    @Autowired
    ReponseEvaluationRepository repRepository;

    @Autowired
    QuestionEvaluationRepository quesRepository;

    @Autowired
    ReponseQuestionRepository repQuesRepository;
    @Autowired
    RubriqueRepository rubriqueRepository;

    @Autowired
    RubriqueEvaluationRepository rubriqueEvaluationRepository;
    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        super(evaluationRepository);
    }

    @Override
    public Evaluation create(Evaluation entity) {

        System.out.println("*******" + entity);
        entity.setNoEnseignant(enseignantRepository.findById(entity.getNoEnseignant().getId()).get());
        entity.setUniteEnseignement(uniteEnseignementRepository.findById(entity.getUniteEnseignement().getId()).get());
        var evaluation = super.create(entity);
        entity.getListeRubriques().forEach( rubriqueEvaluation -> {
            RubriqueEvaluation newRubriqueEvaluation = RubriqueEvaluation
                    .builder()
                    .idEvaluation(evaluation)
                    .idRubrique(rubriqueEvaluation.getIdRubrique())
                    .designation(rubriqueEvaluation.getDesignation())
                    .questionEvaluationList(rubriqueEvaluation.getQuestionEvaluationList())
                    .ordre(rubriqueEvaluation.getOrdre())
                    .build();
//            System.out.println(rubriqueEvaluation);
//            Rubrique rubrique = rubriqueRepository.findById(.getId()).orElseThrow(() -> new EntityNotFoundException("Rubrique not found"));
//            newRubriqueEvaluation.setIdRubrique(rubrique);
//            newRubriqueEvaluation.setIdEvaluation(evaluation);
            rubriqueEvaluationRepository.save(newRubriqueEvaluation);
            System.out.println(newRubriqueEvaluation);
//            Integer rubriqueId = rubriqueEvaluation.getIdRubrique().getId();
//            Rubrique rubrique = rubriqueRepository.findById(rubriqueId).orElseThrow(() -> new EntityNotFoundException("Rubrique not found"));
//            rubriqueEvaluation.setIdRubrique(rubrique);
        });
//         evaluation.setListeRubriques( entity.getListeRubriques());
         return evaluation;
    }

    private void setMoyenne(List<Evaluation> evaluations)
    {
        evaluations.forEach(evaluation -> {
            double moyenne = calculerMoyenne(evaluation.getId());
            evaluation.setMoyenne(moyenne);
        });
    }

    @Override
    public List<Evaluation> findAll() {
        var evaluations = super.findAll();
        setMoyenne(evaluations);
        return evaluations;
    }



    @Override
    public Evaluation read(Integer id) {
        var evaluation = super.read(id);
        double moyenne = calculerMoyenne(evaluation.getId());
        evaluation.setMoyenne(moyenne);
        return evaluation;
    }

    public List<Evaluation> findEvaluationsNonPubliees() {
        var evaluationsNonPubliees = ((EvaluationRepository) repository).findByEtat(Etat.ELA.toString());
        setMoyenne(evaluationsNonPubliees);
        return evaluationsNonPubliees;
    }

    public List<Evaluation> findEvaluationsPubliees() {
        var evaluationsPubliees =  ((EvaluationRepository) repository).findByEtat(Etat.DIS.toString());
        setMoyenne(evaluationsPubliees);
        return evaluationsPubliees;
    }

    // this method retrieve all Current Evaluations for this promotion
    public List<Evaluation> findEvaluationsByPromotion(String code_formation, String annee_universitaire) {
        var promotion = promotionRepository.findById(PromotionId.builder()
                .codeFormation(code_formation)
                .anneeUniversitaire(annee_universitaire)
                .build()).get();
//        return ((EvaluationRepository)repository).findByEtatAndPromotion(Etat.DIS.toString(),promotion);
        var evaluationsPubliees =  ((EvaluationRepository) repository).findByEtat(Etat.DIS.toString());
        return ((EvaluationRepository) repository).findByPromotion(promotion);
    }

    public List<ReponseEvaluation> findAllReponses(Integer id) {
        return repRepository.findByidEvaluation(repository.findById(id).get());
    }

    public double calculerMoyenne(Integer id) {
        List<ReponseEvaluation> reponsesEvaluation = repRepository.findByidEvaluation(repository.findById(id).get());;
        if (reponsesEvaluation == null || reponsesEvaluation.isEmpty()) {
            return 0;
        }

        double sumPositionnement = 0;
        double avg = 0;
        for (ReponseEvaluation reponseEvaluation : reponsesEvaluation) {
            List<ReponseQuestion> reponsesQuestion = reponseEvaluation.getReponseQuestionList();
            sumPositionnement = reponseEvaluation.getReponseQuestionList()
                    .stream()
                    .mapToLong(ReponseQuestion::getPositionnement)
                    .filter(Objects::nonNull)
                    .sum();
            if( reponsesQuestion.size() > 0 )
                avg += sumPositionnement / reponsesQuestion.size();
        }
        return BigDecimal.valueOf(avg)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
//        return 1L;
    }

    public List<Rubrique> findRubriquesNotInEvaluation(Integer evaluationId) {
        return rubriqueRepository.findRubriquesNotInEvaluation(evaluationId);
    }
}
