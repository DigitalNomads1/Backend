package com.dosi.controllers;

import com.dosi.entities.Rubrique;
import com.dosi.entities.RubriqueEvaluation;
import com.dosi.services.RubriqueEvaluationService;
import com.dosi.services.RubriqueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/rubriques")
public class RubriqueController extends GlobalController<Rubrique, Integer> {
    public RubriqueController(RubriqueService service) {
        super(service);
    }
}

