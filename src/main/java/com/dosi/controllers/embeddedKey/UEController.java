package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.Evaluation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.services.EvaluationService;
import com.dosi.services.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.dosi.utils.Constants.API_URL;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(API_URL + "/ue")
public class UEController extends BaseController<UniteEnseignement, UniteEnseignementId> {

    @Autowired
    public UEController(UEService ueService) {
        super(ueService);
    }

    @Autowired
    EvaluationService evaluationService;

    @Override
    public List<UniteEnseignement> getAll() {
        var listUE = super.getAll();
        listUE.forEach(ue -> {
            if( ue.getEvaluationList()!= null  ){
                ue.getEvaluationList().forEach( evaluation -> {
                    double moyenne = evaluationService.calculerMoyenne(evaluation.getId());
                    evaluation.setMoyenne(moyenne);
                });
            }
        });
        return listUE;
    }

    @GetMapping("/{formation}-{ue}")
    public UniteEnseignement read(@PathVariable String formation, @PathVariable String ue) {
        UniteEnseignementId id = UniteEnseignementId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .build();
        try{
            return service.read(id);

        } catch(Exception e){
           throw new ResponseStatusException(NOT_FOUND,"Désolé, nous n'avons pas pu trouver l'unité d'enseignement que vous recherchez avec le code de formation " + id.getCodeFormation() +" et le code UE " +  id.getCodeUe() +" . Veuillez vérifier les informations que vous avez fournies et réessayer.");
        }
    }

    @GetMapping("/{formation}-{ue}/evaluations")
    public List<Evaluation> getAllEvaluations(@PathVariable String formation, @PathVariable String ue) {
        UniteEnseignementId id = UniteEnseignementId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .build();
        return service.read(id).getEvaluationList();
    }

    @GetMapping("/{formation}-{ue}/evaluations/latest")
    public Evaluation getLatestEvaluation(@PathVariable String formation, @PathVariable String ue) {
        UniteEnseignementId id = UniteEnseignementId.builder()
                .codeFormation(formation)
                .codeUe(ue)
                .build();
        Evaluation latestEvaluation = Collections.max(service.read(id).getEvaluationList(), Comparator.comparing(evaluation -> evaluation.getPromotion().getId().getAnneeUniversitaire()));
        return latestEvaluation;
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
