package com.dosi.controllers;

import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.services.FormationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController extends BaseController<Formation, String>{
    public FormationController(FormationService service) {
        super(service);
    }


    @GetMapping("/ue/{id}")
    public List<UniteEnseignement> getUeList(@PathVariable String id){
        return ((FormationService)service).findUEList(id);
    }
}
