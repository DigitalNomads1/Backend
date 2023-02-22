package com.dosi.controllers;

import com.dosi.entities.Enseignant;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.services.EnseignantService;
import com.dosi.services.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unites_enseignement")
public class UEController extends BaseController<UniteEnseignement, UniteEnseignementId> {

    @Autowired
    public UEController(UEService ueService) {
        super(ueService);
    }

}


