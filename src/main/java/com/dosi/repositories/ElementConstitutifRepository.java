package com.dosi.repositories;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.ElementConstitutifId;
import com.dosi.entities.UniteEnseignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementConstitutifRepository extends JpaRepository<ElementConstitutif, ElementConstitutifId> {
    List<ElementConstitutif> findByCodeUE(UniteEnseignement uniteEnseignement);


}