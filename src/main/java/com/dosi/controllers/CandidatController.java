package com.dosi.controllers;

import com.dosi.entities.Candidat;
import com.dosi.services.CandidatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/candidats")
public class CandidatController extends GlobalController<Candidat, String>{
    public CandidatController(CandidatService service) {
        super(service);
    }
}
