package com.dosi.services;

import com.dosi.entities.Qualificatif;
import com.dosi.entities.QuestionEvaluation;
import com.dosi.repositories.QualificatifRepository;
import com.dosi.repositories.QuestionEvaluationRepository;
import org.springframework.stereotype.Service;

@Service
public class QualificatifService extends BaseService<Qualificatif, Integer> {

    public QualificatifService(QualificatifRepository qualificatifRepository) {
        super(qualificatifRepository);
    }

}
