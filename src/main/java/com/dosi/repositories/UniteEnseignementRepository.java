package com.dosi.repositories;

import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UniteEnseignementRepository extends JpaRepository<UniteEnseignement, UniteEnseignementId> {
    List<UniteEnseignement> findByCodeFormation(Formation codeFormation);
}