package com.dosi.controllers;

import com.dosi.entities.*;
import com.dosi.exceptions.ValidationException;
import com.dosi.services.EvaluationService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/evaluations")
public class EvaluationController extends GlobalController<Evaluation, Integer> {
    public EvaluationController(EvaluationService service) {
        super(service);
    }
/*
    @Override
    @PostMapping("/save")
    public Identifiable create(Identifiable entity, BindingResult bindingResult) {
        ((Evaluation)entity).setEtat(Etat.ELA.toString());
        return super.create(entity, bindingResult);
    }*/

    @Override
    @GetMapping("/{id}")
    public Evaluation read(@PathVariable Integer id) {
        ((EvaluationService)service).read(id).getListeRubriques().forEach( rubriqueEvaluation -> {
            System.out.println(rubriqueEvaluation.getQuestionEvaluationList());
        });
        return super.read(id);
    }

    @PostMapping("/save")
    public Evaluation create(@Valid @RequestBody Evaluation entity, BindingResult bindingResult) {
        // Custom implementation for create method
        System.out.println("Custom create method called" + entity);
        System.out.println( entity.getListeRubriques());
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        ((EvaluationService)service).create(entity);
        return (Evaluation)super.create(entity, bindingResult);
    }


    @PostMapping("/publish")
    public Identifiable publishEvaluation(Identifiable entity, BindingResult bindingResult) {
        ((Evaluation)entity).setEtat(Etat.DIS.toString());
        return super.create(entity, bindingResult);
    }

    @GetMapping("/unpublished")
    public List<Evaluation> findNonPubliees() {
        return ((EvaluationService)service).findEvaluationsNonPubliees();
    }

    @GetMapping("/published")
    public List<Evaluation> findPubliees() {
        return ((EvaluationService)service).findEvaluationsPubliees();
    }

    @GetMapping("/promotion/{code_formation}_{annee_universitaire}")
    public List<Evaluation> findEvaluationsByPromotions(@PathVariable String code_formation, @PathVariable String annee_universitaire) {
        return ((EvaluationService) service).findEvaluationsByPromotion(code_formation, annee_universitaire);
    }

    @GetMapping("/{id}/reponses")
    public List<ReponseEvaluation> getAllAnswers(@PathVariable Integer id) {
        return ((EvaluationService) service).findAllReponses(id);
    }

    @GetMapping("/{id}/moyenne")
    public double calculerMoyenne(@PathVariable Integer id) {
        return ((EvaluationService) service).calculerMoyenne(id);
    }

    @GetMapping("/{id}/rubriques_not_in")
    public List<Rubrique> getRubriquesNotInEvaluation(@PathVariable("id") Integer evaluationId) {
        return ((EvaluationService)service).findRubriquesNotInEvaluation(evaluationId);
    }

}