package com.dosi.controllers;

import com.dosi.entities.Formation;
import com.dosi.services.FormationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formations")
public class FormationController extends GlobalController<Formation, String>{
    public FormationController(FormationService service) {
        super(service);
    }

}
