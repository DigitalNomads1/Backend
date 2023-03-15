package com.dosi.repositories;

import com.dosi.entities.Evaluation;
import com.dosi.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

    List<Evaluation> findByEtat(String etat);

    List<Evaluation> findAllByUniteEnseignement_CodeFormation(String codeFormation);

    List<Evaluation> findByEtatAndPromotion(String etat, Promotion promotion);
    List<Evaluation> findByPromotion( Promotion promotion);

    @Query(value = "SELECT * FROM evaluation WHERE ID_EVALUATION IN (SELECT e.ID_EVALUATION FROM ETUDIANT et, Evaluation e, PROMOTION p " +
            "WHERE ID_EVALUATION NOT IN (SELECT  e.ID_EVALUATION  FROM ETUDIANT et, Evaluation e, PROMOTION p, REPONSE_EVALUATION rp " +
            "WHERE et.NO_ETUDIANT=:studentId and et.annee_universitaire=p.annee_universitaire and et.code_formation=p.code_formation " +
            "AND e.code_formation=p.code_formation  and e.annee_universitaire=p.annee_universitaire " +
            "AND rp.id_evaluation=e.id_evaluation AND rp.no_etudiant=et.NO_ETUDIANT ) " +
            "AND et.NO_ETUDIANT=:studentId and et.annee_universitaire=p.annee_universitaire and et.code_formation=p.code_formation " +
            "AND e.code_formation=p.code_formation  and e.annee_universitaire=p.annee_universitaire)", nativeQuery = true)
    List<Evaluation> findAllEvaluationsByStudentId(@Param("studentId") String studentId);

}