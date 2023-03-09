package com.dosi.repositories;

import com.dosi.entities.Evaluation;
import com.dosi.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

    List<Evaluation> findByEtat(String etat);

    List<Evaluation> findAllByUniteEnseignement_CodeFormation(String codeFormation);

    List<Evaluation> findByEtatAndPromotion(String etat, Promotion promotion);
    List<Evaluation> findByPromotion( Promotion promotion);

}