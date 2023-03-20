package com.dosi.controllers;

import com.dosi.entities.Rubrique;
import com.dosi.entities.RubriqueEvaluation;
import com.dosi.entities.RubriqueQuestion;
import com.dosi.services.QuestionService;
import com.dosi.services.RubriqueEvaluationService;
import com.dosi.services.RubriqueQuestionService;
import com.dosi.services.RubriqueService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/rubriques")
public class RubriqueController extends GlobalController<Rubrique, Integer> {
    public RubriqueController(RubriqueService service) {
        super(service);
    }

    @Autowired
    RubriqueQuestionService rubriqueQuestionService;
/*
    public Rubrique get(@PathVariable String designation) {
        System.out.println(designation);
    }
*/
    @RequestMapping(value = "/designation/**", method = RequestMethod.GET)
    public Rubrique moduleStrings(HttpServletRequest request) {

        String requestURL = request.getRequestURL().toString();
        String designation ;
        try {
            designation = URLDecoder.decode(requestURL.split("/designation/")[1], "UTF-8");
//            System.out.println(designation);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        /*var rubriqueQuestionList = rubriqueQuestionService.findAll().stream()
                .filter(rubriqueQuestion -> {
                    System.out.println(rubriqueQuestion.getIdRubrique().getDesignation());
                    return StringUtils.equals( rubriqueQuestion.getIdRubrique().getDesignation() ,designation);
                } )
                .toList();*/
        return ((RubriqueService)service).getRubriqueByDesignation(designation);
//        return rubriqueQuestionList;
    }

    @GetMapping("{id_eval}/moyennes")
    public List<Map> getAllAvgOfQuestions(@PathVariable Integer id_eval) {
        return ((RubriqueService)service).getAllAvgOfRubriques(id_eval);
    }

}

