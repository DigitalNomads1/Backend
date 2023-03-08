package com.dosi.repositories;

import com.dosi.entities.QuestionEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionEvaluationRepository extends JpaRepository<QuestionEvaluation, Integer> {
}