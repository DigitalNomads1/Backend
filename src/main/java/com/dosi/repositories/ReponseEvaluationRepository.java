package com.dosi.repositories;

import com.dosi.entities.ReponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseEvaluationRepository extends JpaRepository<ReponseEvaluation, Integer> {
}