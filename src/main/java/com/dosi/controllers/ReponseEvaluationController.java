package com.dosi.controllers;

import com.dosi.entities.ReponseEvaluation;
import com.dosi.entities.RubriqueEvaluation;
import com.dosi.services.ReponseEvaluationService;
import com.dosi.services.RubriqueEvaluationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/reponseEvaluations")
public class ReponseEvaluationController extends GlobalController<ReponseEvaluation, Integer> {
    public ReponseEvaluationController(ReponseEvaluationService service) {
        super(service);
    }


}

