package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
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
    QuestionEvaluationRepository questionEvaluationRepository;
    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        super(evaluationRepository);
    }

    @Override
    public Evaluation create(Evaluation entity) {

        entity.setNoEnseignant(enseignantRepository.findById(entity.getNoEnseignant().getId()).get());
        var ue = uniteEnseignementRepository.findById(entity.getUniteEnseignement().getId()).get();
        entity.setUniteEnseignement(ue);
        Evaluation evaluation = null;
        try{
            evaluation = super.create(entity);
        } catch (Exception e) {
            if( e.getMessage().contains("DOSI.EVE_EVE_UK"))
            throw new EntityExistsException(  "Vous ne pouvez pas créer une nouvelle évaluation pour l'UE " +  ue.getId().getCodeUe() +" de la promotion " + ue.getCodeFormation().getId()+ "_" + entity.getAnneeUniversitaire() +" car une évaluation pour cette UE existe déjà.");
        }
        Evaluation finalEvaluation = evaluation;
        entity.getListeRubriques().forEach(rubriqueEvaluation -> {
            if( rubriqueEvaluationRepository.findByIdEvaluationAndIdRubrique(finalEvaluation,rubriqueEvaluation.getIdRubrique()).isPresent() )
                throw new EntityExistsException("La rubrique " +rubriqueEvaluation.getIdRubrique().getDesignation() + " déja existe pour cette évaluation!" );
            RubriqueEvaluation newRubriqueEvaluation = RubriqueEvaluation.builder()
                    .idEvaluation(finalEvaluation)
                    .idRubrique(rubriqueEvaluation.getIdRubrique())
                    .designation(rubriqueEvaluation.getDesignation())
                    .questionEvaluationList(rubriqueEvaluation.getQuestionEvaluationList())
                    .ordre(rubriqueEvaluation.getOrdre())
                    .build();
            List<QuestionEvaluation> questionEvaluationList = newRubriqueEvaluation.getQuestionEvaluationList();
            List<QuestionEvaluation> copyOfQuestionEvaluationList = new ArrayList<>(questionEvaluationList); // create a copy of the list
            System.out.println(copyOfQuestionEvaluationList);
            newRubriqueEvaluation = rubriqueEvaluationRepository.save(newRubriqueEvaluation);
            for (QuestionEvaluation questionEvaluation : copyOfQuestionEvaluationList) {
                QuestionEvaluation newQuestionEvaluation = QuestionEvaluation.builder()
                        .idQuestion(questionEvaluation.getIdQuestion())
                        .intitule(questionEvaluation.getIntitule())
                        .ordre(questionEvaluation.getOrdre())
                        .rubriqueEvaluation(newRubriqueEvaluation)
                        .build();
                System.out.println(newQuestionEvaluation);
                questionEvaluationRepository.save(newQuestionEvaluation);
            }
            System.out.println(newRubriqueEvaluation);
        });
        return read(evaluation.getId());
    }

    @Override
    public Evaluation update(Evaluation entity) {
        System.out.println("UPDATE----------------------");
        System.out.println(entity);
        System.out.println("-------------------------");
        List<RubriqueEvaluation> newListeRubriques = new ArrayList<>();
        entity.getListeRubriques().forEach( rubriqueEvaluation -> {
            if( rubriqueEvaluationRepository.findByIdEvaluationAndIdRubrique(entity,rubriqueEvaluation.getIdRubrique()).isPresent() )
                throw new EntityExistsException("La rubrique " +rubriqueEvaluation.getIdRubrique().getDesignation() + " existe déja dans cette évaluation!" );
            rubriqueEvaluation.setId(null);
            RubriqueEvaluation newRubriqueEvaluation = RubriqueEvaluation.builder()
                    .idEvaluation(entity)
                    .idRubrique(rubriqueEvaluation.getIdRubrique())
                    .designation(rubriqueEvaluation.getDesignation())
                    .questionEvaluationList(rubriqueEvaluation.getQuestionEvaluationList())
                    .ordre(rubriqueEvaluation.getOrdre())
                    .idEvaluation(entity)
                    .build();

            System.out.println();
            RubriqueEvaluation finalNewRubriqueEvaluation = newRubriqueEvaluation;

            System.out.println("5****************************************************************");
            newRubriqueEvaluation = rubriqueEvaluationRepository.save(newRubriqueEvaluation);
            newRubriqueEvaluation.getQuestionEvaluationList().forEach(questionEvaluation -> {
                System.out.println("3****************************************************************");
                questionEvaluation.setRubriqueEvaluation(finalNewRubriqueEvaluation);
                System.out.println(questionEvaluation);
                System.out.println(finalNewRubriqueEvaluation); // **********
                QuestionEvaluation temp = questionEvaluationRepository.save(questionEvaluation);
                System.out.println(temp);
                System.out.println("4****************************************************************");

            });
            newListeRubriques.add(newRubriqueEvaluation);
        });
        System.out.println("6****************************************************************");

        entity.setListeRubriques(null);
        super.update(entity);
        return super.read(entity.getId());
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
        return ((EvaluationRepository) repository).findByPromotion(promotion);
    }

    public List<Evaluation> findEvaluationsByPromotionAndUE(String code_formation, String annee_universitaire, String codeUE) {
        var promotion = findEvaluationsByPromotion(code_formation, annee_universitaire);
        var evalsByUeAndPromotion = promotion.stream()
                .filter(e -> StringUtils.equals(e.getUniteEnseignement().getId().getCodeFormation(), code_formation)
                        && StringUtils.equals( e.getUniteEnseignement().getId().getCodeUe(), codeUE ))
                .toList();
//        return ((EvaluationRepository)repository).findByEtatAndPromotion(Etat.DIS.toString(),promotion);
//        var evaluationsPubliees =  ((EvaluationRepository) repository).findByEtat(Etat.DIS.toString());
        return evalsByUeAndPromotion;
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
