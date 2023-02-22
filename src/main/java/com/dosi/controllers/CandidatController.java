package com.dosi.controllers;

import com.dosi.entities.Candidat;
import com.dosi.services.CandidatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/candidats")
public class CandidatController extends BaseController<Candidat, String>{
    public CandidatController(CandidatService service) {
        super(service);
    }
}
