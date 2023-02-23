package com.dosi.repositories;

import com.dosi.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {


}