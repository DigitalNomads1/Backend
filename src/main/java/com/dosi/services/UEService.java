package com.dosi.services;

import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.FormationRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import org.springframework.stereotype.Service;

@Service
public class UEService extends BaseService<UniteEnseignement, UniteEnseignementId> {

    public UEService(UniteEnseignementRepository repository, FormationRepository formationRepository) {
        super(repository);
    }

    @Override
    public void delete(UniteEnseignementId id) {
        super.delete(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicationException("Veuillez vérifier les données enregistrées, Vérifier que l'UE n'a pas des EC ou Evaluation.");
        }
    }
}
