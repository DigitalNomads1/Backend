package com.dosi.repositories;

import com.dosi.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
        Optional<Enseignant> findByEmailUbo(String email);
        List<Enseignant> findByEmailPerso(String email);

}