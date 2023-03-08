package com.dosi.repositories;

import com.dosi.entities.ReponseQuestion;
import com.dosi.entities.ReponseQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseQuestionRepository extends JpaRepository<ReponseQuestion, ReponseQuestionId> {
}