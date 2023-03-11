package com.dosi.repositories;

import com.dosi.entities.QuestionEvaluation;
import com.dosi.entities.ReponseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionEvaluationRepository extends JpaRepository<QuestionEvaluation, Integer> {
}