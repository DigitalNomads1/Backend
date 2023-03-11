package com.dosi.repositories;

import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniteEnseignementRepository extends JpaRepository<UniteEnseignement, UniteEnseignementId> {
    List<UniteEnseignement> findByCodeFormation(Formation codeFormation);
}