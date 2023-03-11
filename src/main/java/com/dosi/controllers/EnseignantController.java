package com.dosi.controllers;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Enseignant;
import com.dosi.entities.Promotion;
import com.dosi.entities.UniteEnseignement;
import com.dosi.services.EnseignantService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/enseignants")
public class EnseignantController extends GlobalController<Enseignant, Long> {
    public EnseignantController(EnseignantService service) {
        super(service);
    }

    @Override
    public List getAll() {
        for (Object enseignant : super.getAll())
            System.out.println( ((Enseignant)enseignant).getListEC() );
        return super.getAll();
    }

    /**
     * @param id Long
     * @return List<UniteEnseignement>
     */
    @GetMapping("/{id}/UE")
    public List<UniteEnseignement> getUE(@PathVariable Long id) {
        return ((EnseignantService)service).getUE(id);
    }

    /**
     * @param id Long
     * @return List<ElementConstitutif>
     */
    @GetMapping("/{id}/EC")
    public List<ElementConstitutif> getEC(@PathVariable Long id) {
        return ((EnseignantService)service).getEC(id);
    }

    /**
     * @param id
     * @return List<Promotion>
     */
    @GetMapping("/{id}/promotions")
    public List<Promotion> getPromotions(@PathVariable Long id) {
        return ((EnseignantService)service).getPromotions(id);

    }


}

