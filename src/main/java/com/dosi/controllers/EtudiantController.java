
package com.dosi.controllers;

import com.dosi.entities.Etudiant;
import com.dosi.services.EtudiantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController extends BaseController<Etudiant,String>  {
    public EtudiantController(EtudiantService service) {
        super(service);
    }


}

