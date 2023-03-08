package com.dosi.repositories;

import com.dosi.entities.RubriqueQuestion;
import com.dosi.entities.RubriqueQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubriqueQuestionRepository extends JpaRepository<RubriqueQuestion, RubriqueQuestionId> {
}
