package com.dosi.repositories;

import com.dosi.entities.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubriqueRepository extends JpaRepository<Rubrique, Integer> {

    Rubrique findByDesignation(String designation);

    @Query("SELECT r FROM Rubrique r WHERE r.id " +
            "NOT IN (SELECT re.idRubrique.id FROM RubriqueEvaluation re " +
            "WHERE re.idEvaluation.id = :eval) ORDER BY r.ordre")
    List<Rubrique> findRubriquesNotInEvaluation(@Param("eval") Integer eval);

}