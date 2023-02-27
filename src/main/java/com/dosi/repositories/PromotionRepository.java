package com.dosi.repositories;

import com.dosi.entities.Promotion;
import com.dosi.entities.PromotionId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, PromotionId> {

    @Transactional
    @Modifying
    @Query(value = "delete from promotion p where p.ANNEE_UNIVERSITAIRE= ?1 and CODE_FORMATION= ?2",nativeQuery = true)
    void deleteByEmbeddedId(String annee,String formation);
}
