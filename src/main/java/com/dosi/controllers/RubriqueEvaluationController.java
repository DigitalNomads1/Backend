package com.dosi.controllers;

import com.dosi.entities.*;
import com.dosi.services.EnseignantService;
import com.dosi.services.RubriqueEvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/rubriqueEvaluations")
public class RubriqueEvaluationController extends GlobalController<RubriqueEvaluation, Integer> {
    public RubriqueEvaluationController(RubriqueEvaluationService service) {
        super(service);
    }

}

