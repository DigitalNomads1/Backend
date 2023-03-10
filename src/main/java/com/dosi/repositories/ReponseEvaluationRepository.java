package com.dosi.repositories;

import com.dosi.entities.Evaluation;
import com.dosi.entities.ReponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseEvaluationRepository extends JpaRepository<ReponseEvaluation, Integer> {
    List<ReponseEvaluation> findByidEvaluation(Evaluation idEvaluation);
}
