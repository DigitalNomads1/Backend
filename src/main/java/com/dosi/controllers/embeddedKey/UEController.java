package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.services.UEService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/unites_enseignement")
public class UEController extends BaseController<UniteEnseignement, UniteEnseignementId> {

    @Autowired
    public UEController(UEService ueService) {
        super(ueService);
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


