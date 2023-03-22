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
            ((ElementConstitutifRepository)repository).deleteByCodeFormationAndCodeUEANDCodeEC(id.getCodeFormation(), id.getCodeUe(), id.getCodeEc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ApplicationException("Une erreur est survenue lors de traiement de votre requête. Veuillez réessayer plus tard.");
        }
    }
}