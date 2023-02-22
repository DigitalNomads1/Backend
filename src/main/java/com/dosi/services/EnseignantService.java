package com.dosi.services;

import com.dosi.entities.Enseignant;
import com.dosi.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnseignantService extends BaseService<Enseignant, Long> {
    private final EnseignantRepository enseignantRepository;

    @Autowired
    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
        super.setRepository(enseignantRepository);
    }

    // add methods to implement business logic using the repository
}
