package com.dosi.repositories;

import com.dosi.entities.Candidat;
import com.dosi.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, String> {
    Candidat findByEmail(String email);
}