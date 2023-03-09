package com.dosi.controllers;

import com.dosi.entities.*;
import com.dosi.services.EvaluationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/evaluations")
public class EvaluationController extends GlobalController<Evaluation, Integer> {
    public EvaluationController(EvaluationService service) {
        super(service);
    }

    @Override
    @PostMapping("/save")
    public Identifiable create(Identifiable entity, BindingResult bindingResult) {
        ((Evaluation)entity).setEtat(Etat.ELA.toString());
        return super.create(entity, bindingResult);
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
        System.out.println(code_formation + " " + annee_universitaire);
        return ((EvaluationService) service).findEvaluationsByPromotion(code_formation, annee_universitaire);
    }

    @GetMapping("/{id}/reponses")
    public List<ReponseEvaluation> getAllAnswers(Integer id) {
        return ((EvaluationService) service).findAllReponses(id);
    }

}