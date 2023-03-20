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

    @Query(value = "SELECT re.id_rubrique, ROUND(AVG(positionnement), 2) positionnement " +
            "FROM rubrique r, evaluation e, rubrique_evaluation re, question_evaluation qe, reponse_question rq " +
            "WHERE e.id_evaluation = :id_evaluation " +
            "AND re.id_evaluation = e.id_evaluation " +
            "AND re.id_rubrique = r.id_rubrique " +
            "AND qe.id_rubrique_evaluation = re.id_rubrique_evaluation " +
            "AND qe.id_question_evaluation = rq.id_question_evaluation " +
            "GROUP BY re.id_rubrique", nativeQuery = true)
    List<List> findAvgOfEveryRubrique(@Param("id_evaluation") Integer id_evaluation);

}