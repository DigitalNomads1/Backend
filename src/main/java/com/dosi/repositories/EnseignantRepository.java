package com.dosi.repositories;

import com.dosi.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
        Enseignant findByEmailUbo(String email);
}