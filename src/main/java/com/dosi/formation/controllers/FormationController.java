package com.dosi.formation.controllers;


import com.dosi.entities.Formation;
import com.dosi.formation.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formation")
public class FormationController {

    @Autowired
    FormationService formationService;

    @GetMapping("")
    public List<Formation> getFormations() {
        return  formationService.getAllFormation();
    }

    @GetMapping("/id/{id}")
    public Optional<Formation> getFormationById(@PathVariable String id){
       return formationService.findById(id);
    }

    @GetMapping("/nom/{name}")
    public Optional<Formation> getFormationByNom(@PathVariable String nom){
        return Optional.ofNullable(formationService.findByName(nom));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createFormation(Formation formation){
        formationService.createFormation(formation);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteFormationById(@PathVariable String id){
        formationService.deleteFormation(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateFormation(Formation formation){
        formationService.updateFormation(formation);
        return ResponseEntity.ok().build();
           }

}
