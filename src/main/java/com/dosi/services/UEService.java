package com.dosi.services;

import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

@Service
public class UEService extends BaseService<UniteEnseignement, UniteEnseignementId> {

    public UEService(UniteEnseignementRepository repository) {
        super(repository);
    }

    @Override
    public UniteEnseignement create(UniteEnseignement uniteEnseignement){
        if(uniteEnseignement.getId() != null ){
            if( repository.existsById((UniteEnseignementId)uniteEnseignement.getId())){
                throw new EntityExistsException("Entité" + uniteEnseignement+"existe déja");
            }
        }
        return  repository.save(uniteEnseignement);
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
