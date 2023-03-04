package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.ElementConstitutifId;
import com.dosi.services.ECService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ec")
public class ECController  extends BaseController<ElementConstitutif, ElementConstitutifId> {

    @Autowired
    public ECController(ECService service) {
        super(service);
    }

    @GetMapping("/{formation}-{ue}-{ec}")
    public ElementConstitutif read(@PathVariable String formation, @PathVariable String ue, @PathVariable String ec) {
        ElementConstitutifId id = ElementConstitutifId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .codeEc(ec)
                .build();
        return service.read(id);
    }

    @DeleteMapping("/{formation}-{ue}-{ec}")
    public void delete(@PathVariable String formation, @PathVariable String ue, @PathVariable String ec) {
        ElementConstitutifId id = ElementConstitutifId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .codeEc(ec)
                .build();
        service.delete(id);
    }


}
