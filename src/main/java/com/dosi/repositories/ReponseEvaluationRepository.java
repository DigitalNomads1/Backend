package com.dosi.repositories;

import com.dosi.entities.ReponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseEvaluationRepository extends JpaRepository<ReponseEvaluation, Integer> {
    @Query("SELECT r FROM ReponseEvaluation r JOIN r.idEvaluation e WHERE e.id = :idEvaluation")
    List<ReponseEvaluation> findByEvaluationId(@Param("idEvaluation") Integer idEvaluation);}