package com.dosi.controllers;

import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import com.dosi.services.FormationService;
import com.dosi.services.PromotionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
public class PromotionController extends BaseController<Promotion, PromotionId>{
    public PromotionController(PromotionService service) {
        super(service);
    }

}
