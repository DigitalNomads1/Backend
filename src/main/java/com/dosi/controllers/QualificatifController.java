package com.dosi.controllers;

import com.dosi.entities.Qualificatif;
import com.dosi.entities.Question;
import com.dosi.services.QualificatifService;
import com.dosi.services.QuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/qualificatifs")
public class QualificatifController extends GlobalController<Qualificatif, Integer> {
    public QualificatifController(QualificatifService service) {
        super(service);
    }


}

