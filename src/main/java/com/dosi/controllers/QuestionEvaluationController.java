package com.dosi.controllers;

import com.dosi.entities.QuestionEvaluation;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.services.QuestionEvaluationService;
import com.dosi.services.ReponseEvaluationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/questionEvaluations")
public class QuestionEvaluationController extends GlobalController<QuestionEvaluation, Integer> {
    public QuestionEvaluationController(QuestionEvaluationService service) {
        super(service);
    }


}

