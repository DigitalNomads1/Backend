package com.dosi.controllers;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Enseignant;
import com.dosi.entities.Promotion;
import com.dosi.entities.UniteEnseignement;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.services.EnseignantService;
import com.dosi.services.FormationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController extends GlobalController<Enseignant, Long> {
    public EnseignantController(EnseignantService service) {
        super(service);
    }

    @GetMapping("/{id}/UE")
    public List<UniteEnseignement> getUE(@PathVariable Long id) {
        return ((EnseignantService)service).getUE(id);
    }

    @GetMapping("/{id}/EC")
    public List<ElementConstitutif> getEC(@PathVariable Long id) {
        return ((EnseignantService)service).getEC(id);
    }

    @GetMapping("/{id}/promotions")
    public List<Promotion> getPromotions(@PathVariable Long id) {
        return ((EnseignantService)service).getPromotions(id);

    }


}

