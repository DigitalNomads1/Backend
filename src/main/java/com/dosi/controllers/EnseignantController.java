package com.dosi.controllers;

import com.dosi.entities.Enseignant;
import com.dosi.services.EnseignantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController extends BaseController<Enseignant, Long> {
    public EnseignantController(EnseignantService service) {
        super(service);
    }
}

