package com.dosi.repositories;

import com.dosi.entities.Qualificatif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QualificatifRepository extends JpaRepository<Qualificatif, Integer> {
}