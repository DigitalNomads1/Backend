package com.dosi.services;

import com.dosi.entities.RubriqueQuestion;
import com.dosi.entities.RubriqueQuestionId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.RubriqueQuestionRepository;
import org.springframework.stereotype.Service;


@Service
public class RubriqueQuestionService extends BaseService<RubriqueQuestion, RubriqueQuestionId> {
    public RubriqueQuestionService(RubriqueQuestionRepository repository) {
        super(repository);
    }

    /**
     * @param id RubriqueQuestionId
     */
    @Override
    public void delete(RubriqueQuestionId id) {
        super.delete(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicationException("Veuillez vérifier les données enregistrées");
        }
    }

    public RubriqueQuestionId buildRubriqueQuestionId(int id_rubrique, int id_question) {
        RubriqueQuestionId id = RubriqueQuestionId.builder()
                .idRubrique(id_rubrique)
                .idQuestion(id_question)
                .build();
        return id;
    }

}