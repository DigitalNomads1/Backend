package com.dosi.repositories;

import com.dosi.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Etudiant findByEmail(String email);
    Etudiant findByEmailUbo(String email);

}