package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.ElementConstitutifRepository;
import org.springframework.stereotype.Service;


@Service
public class ECService extends BaseService<ElementConstitutif, ElementConstitutifId> {
    public ECService(ElementConstitutifRepository repository) {
        super(repository);
    }

    /**
     * @param id ElementConstitutifId
     */
    @Override
    public void delete(ElementConstitutifId id) {
        super.delete(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicationException("Veuillez vérifier les données enregistrées");
        }
    }
}