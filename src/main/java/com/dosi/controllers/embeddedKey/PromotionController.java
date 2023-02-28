package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.repositories.PromotionRepository;
import com.dosi.services.PromotionService;
import com.dosi.services.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionController extends BaseController<Promotion, PromotionId> {
    @Autowired
    PromotionService promotionService;
    public PromotionController(PromotionService promotionService) {
        super(promotionService);
    }
    @GetMapping("/{annee}-{formation}")
    public Promotion read(@PathVariable String annee, @PathVariable String formation) {
        PromotionId id= PromotionId.builder()
                .anneeUniversitaire(annee)
                .codeFormation(formation)
                .build();
        return service.read(id);
    }

    @DeleteMapping("/{annee}-{formation}")
    public void delete(@PathVariable String annee, @PathVariable String formation) {
        PromotionId id= PromotionId.builder()
                .anneeUniversitaire(annee)
                .codeFormation(formation)
                .build();
            promotionService.deleteByEmbeddedId(id.getAnneeUniversitaire(), id.getCodeFormation());

    }
}
