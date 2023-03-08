package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.services.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/ue")
public class UEController extends BaseController<UniteEnseignement, UniteEnseignementId> {

    @Autowired
    public UEController(UEService ueService) {
        super(ueService);
    }

    @Override
    public List<UniteEnseignement> getAll() {
        System.out.println(super.getAll());
        return super.getAll();
    }

    @GetMapping("/{formation}-{ue}")
    public UniteEnseignement read(@PathVariable String formation, @PathVariable String ue) {
        UniteEnseignementId id = UniteEnseignementId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .build();
        return service.read(id);
    }

    @DeleteMapping("/{formation}-{ue}")
    public void delete(@PathVariable String formation, @PathVariable String ue) {
        UniteEnseignementId id = UniteEnseignementId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .build();
        service.delete(id);
    }
}
