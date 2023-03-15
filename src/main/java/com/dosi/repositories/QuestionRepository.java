package com.dosi.repositories;

import com.dosi.entities.Evaluation;
import com.dosi.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT  qe.id_question, ROUND(AVG(positionnement),2) positionnement " +
            "FROM reponse_question rp, REPONSE_EVALUATION re,Question_EVALUATION qe, EVALUATION e " +
            "WHERE e.id_evaluation = :id_evaluation " +
            "WHERE rp.id_question_evaluation=qe.id_question_evaluation AND rp.id_reponse_evaluation=re.id_reponse_evaluation " +
            "AND re.id_evaluation=e.id_evaluation " +
            "GROUP BY qe.id_question " +
            "ORDER BY qe.id_question", nativeQuery = true)
    Map<Integer, Integer> findAvgOfEveryQuestion(@Param("id_evaluation") Integer id_evaluation);
}