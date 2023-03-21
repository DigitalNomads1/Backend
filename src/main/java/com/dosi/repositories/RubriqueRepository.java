package com.dosi.repositories;

import com.dosi.entities.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubriqueRepository extends JpaRepository<Rubrique, Integer> {

    Rubrique findByDesignation(String designation);

    @Query("SELECT r FROM Rubrique r WHERE r.id " +
            "NOT IN (SELECT re.idRubrique.id FROM RubriqueEvaluation re " +
            "WHERE re.idEvaluation.id = :eval) ORDER BY r.ordre")
    List<Rubrique> findRubriquesNotInEvaluation(@Param("eval") Integer eval);

    @Query(value = "SELECT qe.id_rubrique_evaluation ,ROUND(AVG(positionnement),2) positionnement " +
            "FROM reponse_question rp, REPONSE_EVALUATION re, Question_EVALUATION qe, EVALUATION e , RUBRIQUE_EVALUATION rubEval " +
            "WHERE e.id_evaluation = :id_evaluation " +
            "AND rp.id_question_evaluation = qe.id_question_evaluation AND rp.id_reponse_evaluation = re.id_reponse_evaluation " +
            "AND re.id_evaluation = e.id_evaluation " +
            "AND qe.id_rubrique_evaluation=rubEval.id_rubrique_evaluation " +
            "GROUP BY ( qe.id_rubrique_evaluation) " +
            "ORDER BY qe.id_rubrique_evaluation", nativeQuery = true)
    List<List> findAvgOfEveryRubrique(@Param("id_evaluation") Integer id_evaluation);

}