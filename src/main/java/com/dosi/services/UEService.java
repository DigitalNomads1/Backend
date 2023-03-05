package com.dosi.services;

import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UEService extends BaseService<UniteEnseignement, UniteEnseignementId> {

    @Autowired
    EnseignantRepository enseignantRepository;
    public UEService(UniteEnseignementRepository repository) {
        super(repository);
    }

    @Override
    public List<UniteEnseignement> findAll() {
        System.out.println(super.findAll());
        for( UniteEnseignement ue : super.findAll()) {
            ue.setNoEnseignant(enseignantRepository.findById(ue.getNoEnseignant().getId() ).get());
        }
        return super.findAll();
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
