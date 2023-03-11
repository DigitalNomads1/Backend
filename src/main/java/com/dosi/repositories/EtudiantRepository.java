package com.dosi.repositories;

import com.dosi.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Optional<Etudiant> findByEmail(String email);
    Optional<Etudiant> findByEmailUbo(String email);

}