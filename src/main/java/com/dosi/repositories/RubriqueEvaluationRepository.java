package com.dosi.repositories;

import com.dosi.entities.Evaluation;
import com.dosi.entities.Rubrique;
import com.dosi.entities.RubriqueEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RubriqueEvaluationRepository extends JpaRepository<RubriqueEvaluation, Integer> {

    Optional<RubriqueEvaluation> findByIdEvaluationAndIdRubrique(Evaluation idEvaluation, Rubrique idRubrique);

}