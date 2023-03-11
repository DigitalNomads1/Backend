package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.BaseController;
import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.ElementConstitutifId;
import com.dosi.entities.RubriqueQuestion;
import com.dosi.entities.RubriqueQuestionId;
import com.dosi.services.ECService;
import com.dosi.services.RubriqueQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/rubriqueQuestions")
public class RubriqueQuestionController extends BaseController<RubriqueQuestion, RubriqueQuestionId> {

    @Autowired
    public RubriqueQuestionController(RubriqueQuestionService service) {
        super(service);
    }

    @Override
    public List<RubriqueQuestion> getAll() {
        System.out.println(super.getAll());
        return super.getAll();
    }



    @GetMapping("/{id_rubrique}-{id_question}")
    public RubriqueQuestion read(@PathVariable int id_rubrique, @PathVariable int id_question) {
        RubriqueQuestionId id = ((RubriqueQuestionService)service).buildRubriqueQuestionId(id_rubrique, id_question);
        return service.read(id);
    }



    @DeleteMapping("/{id_rubrique}-{id_question}")
    public void delete(@PathVariable int id_rubrique, @PathVariable int id_question) {
        RubriqueQuestionId id = ((RubriqueQuestionService)service).buildRubriqueQuestionId(id_rubrique, id_question);
        service.delete(id);
    }


}
