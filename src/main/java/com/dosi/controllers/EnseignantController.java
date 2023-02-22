package com.dosi.controllers;

import com.dosi.entities.Enseignant;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController extends BaseController<Enseignant, Long> {

    private EnseignantRepository enseignantRepository;

    @Autowired
    public EnseignantController(EnseignantService enseignantService) {
        super(enseignantService);
    }

}


