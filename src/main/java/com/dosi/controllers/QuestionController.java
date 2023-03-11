package com.dosi.controllers;

import com.dosi.entities.Question;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.services.QuestionService;
import com.dosi.services.ReponseEvaluationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/questions")
public class QuestionController extends GlobalController<Question, Integer> {
    public QuestionController(QuestionService service) {
        super(service);
    }


}

