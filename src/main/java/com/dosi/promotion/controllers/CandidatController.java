package com.dosi.promotion.controllers;

import com.dosi.controllers.BaseController;
import com.dosi.entities.Candidat;
import com.dosi.promotion.services.CandidatService;
import com.dosi.services.EnseignantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/candidats")
public class CandidatController extends BaseController<Candidat, String>{
    public CandidatController(CandidatService service) {
        super(service);
    }
}
