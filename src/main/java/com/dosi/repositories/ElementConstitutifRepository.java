package com.dosi.repositories;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.ElementConstitutifId;
import com.dosi.entities.UniteEnseignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, ElementConstitutifId> {
    List<ElementConstitutif> findByCodeUE(UniteEnseignement uniteEnseignement);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Element_Constitutif WHERE CODE_FORMATION=:codeFormation AND CODE_UE=:codeUE AND CODE_EC=:codeEC ", nativeQuery = true)
    void deleteByCodeFormationAndCodeUEANDCodeEC(@Param("codeFormation") String codeFormation, @Param("codeUE") String codeUE, @Param("codeEC") String codeEC);

}