package com.dosi.services;

import com.dosi.entities.Enseignant;
import com.dosi.repositories.EnseignantRepository;
import org.springframework.stereotype.Service;

@Service
public class EnseignantService extends BaseService<Enseignant, Long> {

    public EnseignantService(EnseignantRepository enseignantRepository) {
        super(enseignantRepository);
    }

    // add methods to implement business logic using the repository
}
