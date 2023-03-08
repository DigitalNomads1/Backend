package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import com.dosi.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/promotions")
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
//            promotionService.deleteByEmbeddedId(id.getAnneeUniversitaire(), id.getCodeFormation());

    }

  /*  @DeleteMapping("/{annee}-{formation}")
    public void delete(@PathVariable String annee, @PathVariable String formation) {
        PromotionId id = new PromotionId(annee, formation);
        Promotion promotion = myEntityRepository.findById(id).orElse(null);
        if (promotion != null) {
            myEntityRepository.delete(promotion);
        }
    }*/





}
