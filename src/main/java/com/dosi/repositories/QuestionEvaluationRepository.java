package com.dosi.repositories;

import com.dosi.entities.QuestionEvaluation;
import com.dosi.entities.ReponseEvaluation;
import com.dosi.entities.RubriqueEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface QuestionEvaluationRepository extends JpaRepository<QuestionEvaluation, Integer> {

    List<QuestionEvaluation> findByRubriqueEvaluation(RubriqueEvaluation rubriqueEvaluation);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Question_Evaluation WHERE id_rubrique_evaluation=:idRubriqueEvaluation ", nativeQuery = true)
    int deleteByIdRubriqueEvaluation(@Param("idRubriqueEvaluation") int idRubriqueEvaluation);
}