package com.dosi.controllers.embeddedKey;

import com.dosi.controllers.GlobalController;
import com.dosi.entities.Question;
import com.dosi.entities.ReponseQuestion;
import com.dosi.entities.ReponseQuestionId;
import com.dosi.services.QuestionService;
import com.dosi.services.ReponseQuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/reponseQuestions")
public class ReponseQuestionController extends GlobalController<ReponseQuestion, ReponseQuestionId> {
    public ReponseQuestionController(ReponseQuestionService service) {
        super(service);
    }
}

