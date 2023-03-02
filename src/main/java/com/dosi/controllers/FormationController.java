package com.dosi.controllers;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.services.FormationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/formations")
public class FormationController extends GlobalController<Formation, String>{
    public FormationController(FormationService service) {
        super(service);
    }


    @GetMapping("/{id}/ue")
    public List<UniteEnseignement> getUeList(@PathVariable String id){
        return ((FormationService)service).findUEList(id);
    }

    @GetMapping("/{id_formation}/ue/{id_ue}/ec")
    public List<ElementConstitutif> getEcList(@PathVariable String id_formation, @PathVariable String id_ue) {
        return  ((FormationService)service).findECList(id_formation,id_ue);
    }
}
