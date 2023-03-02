package com.dosi.services;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Enseignant;
import com.dosi.entities.Promotion;
import com.dosi.entities.UniteEnseignement;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService extends BaseService<Enseignant, Long> {

    public EnseignantService(EnseignantRepository enseignantRepository) {
        super(enseignantRepository);
    }

    public List<UniteEnseignement> getUE( Long id) {
        return read(id).getListUE();
    }

    public List<ElementConstitutif> getEC(Long id) { return read(id).getListEC();}

    public List<Promotion> getPromotions(Long id) {
        return read(id).getListPromotion();
    }
    public Enseignant findByEmailUbo(String email){
        return ((EnseignantRepository)repository).findByEmailUbo(email);
    }

    public Enseignant create(Enseignant enseignant) {
        if(enseignant.getId() != null )
        {
            if (repository.existsById((Long)enseignant.getId())) {
                throw new EntityExistsException("Entité " + enseignant + " existe déjà!");
            }
        }
        if(findByEmailUbo(enseignant.getEmailUbo())!=null)
        {
            throw new EntityExistsException("Email existe déjà!");
        }

        return repository.save(enseignant);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw new ApplicationException("Veuillez vérifier les données enregistrées, Vérifier que l'enseignant n'est pas un responsable d'une Promotion, UE ou EC.");
        }
    }
}
