package com.dosi.services;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Enseignant;
import com.dosi.entities.Promotion;
import com.dosi.entities.UniteEnseignement;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Enseignant update(Enseignant enseignant) {
        Enseignant enseignant1=repository.findById(enseignant.getId()).orElse(null);

        if (enseignant1 == null || !enseignant.getEmailUbo().equals(enseignant1.getEmailUbo()))
        if(findByEmailUbo(enseignant.getEmailUbo())!=null)
            throw new EntityExistsException("Email existe déjà!");

        return repository.save(enseignant);
    }


}
