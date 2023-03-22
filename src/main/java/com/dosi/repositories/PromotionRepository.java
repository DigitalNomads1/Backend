package com.dosi.repositories;

import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, PromotionId> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM promotion WHERE CODE_FORMATION=:codeFormation AND ANNEE_UNIVERSITAIRE=:anneeUniversitaire ", nativeQuery = true)
    void deletePromotionByCodeFormationAndAnneeUniversitaire(@Param("codeFormation") String codeFormation, @Param("anneeUniversitaire") String anneeUniversitaire);

}
