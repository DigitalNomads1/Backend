
package com.dosi.controllers;

import com.dosi.entities.Etudiant;
import com.dosi.entities.Evaluation;
import com.dosi.repositories.EvaluationRepository;
import com.dosi.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/etudiants")
public class EtudiantController extends GlobalController<Etudiant,String>  {
    public EtudiantController(EtudiantService service) {
        super(service);
    }

    @GetMapping("/{id}/evaluations")
    public List<Evaluation> getAllEvaluations(@PathVariable String id) {
        return ((EtudiantService )service).findAllEvaluationsByStudentId(id);
    }
}

