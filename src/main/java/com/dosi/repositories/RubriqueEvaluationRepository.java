package com.dosi.repositories;

import com.dosi.entities.RubriqueEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubriqueEvaluationRepository extends JpaRepository<RubriqueEvaluation, Integer> {
}