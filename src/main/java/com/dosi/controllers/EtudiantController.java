
package com.dosi.controllers;

import com.dosi.entities.Etudiant;
import com.dosi.services.EtudiantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/etudiants")
public class EtudiantController extends GlobalController<Etudiant,String>  {
    public EtudiantController(EtudiantService service) {
        super(service);
    }


}

