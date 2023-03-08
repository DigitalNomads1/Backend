package com.dosi.services;

import com.dosi.entities.ElementConstitutif;
import com.dosi.entities.Enseignant;
import com.dosi.entities.Promotion;
import com.dosi.entities.UniteEnseignement;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EnseignantRepository;
import jakarta.persistence.EntityExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnseignantService extends BaseService<Enseignant, Long> {

    public EnseignantService(EnseignantRepository enseignantRepository) {
        super(enseignantRepository);
    }

    /**
     * @param id Long
     * @return List<UniteEnseignement>
     */
    public List<UniteEnseignement> getUE(Long id) {
        return read(id).getListUE();
    }

    /**
     * @param id Long
     * @return List<ElementConstitutif>
     */
    public List<ElementConstitutif> getEC(Long id) {
        return read(id).getListEC();
    }

    /**
     * @param id Long
     * @return List<Promotion>
     */
    public List<Promotion> getPromotions(Long id) {
        return read(id).getListPromotion();
    }

    /**
     * @param enseignant
     * @return Enseignant
     */
    @Override
    public Enseignant create(Enseignant enseignant) {
        if (enseignant.getId() != null) {
            if (repository.existsById((Long) enseignant.getId())) {
                throw new EntityExistsException("L'enseignant " + enseignant.getNom().toUpperCase() + " " + StringUtils.capitalize(enseignant.getPrenom())  + " existe déjà!");
            }
        }
        if (((EnseignantRepository) repository).findByEmailUbo(enseignant.getEmailUbo()).size() > 0 ) {
            throw new EntityExistsException("L'email académique " + enseignant.getEmailUbo() + " existe déjà!");
        }

        if (((EnseignantRepository) repository).findByEmailPerso(enseignant.getEmailPerso()).size() >0) {
            throw new EntityExistsException("l'email " + enseignant.getEmailPerso() + " existe déjà!");
        }

        return repository.save(enseignant);
    }

    @Override
    public Enseignant update(Enseignant entity) {
        Enseignant enseignant = repository.findById(entity.getId()).orElse(null);
        if (enseignant == null || !(StringUtils.equals(entity.getEmailUbo(), enseignant.getEmailUbo())))
            if (((EnseignantRepository) repository).findByEmailUbo(entity.getEmailUbo()).size() > 0 )
                throw new EntityExistsException("L'email académique " + entity.getEmailUbo() + " existe déjà!");
        if (entity.getEmailPerso() == null)
            return repository.save(entity);
        if (enseignant == null || !(StringUtils.equals(entity.getEmailPerso(), enseignant.getEmailPerso())))
        {
            if (((EnseignantRepository) repository).findByEmailPerso(entity.getEmailPerso()).size() > 0)
                throw new EntityExistsException("L'email personnel " + entity.getEmailPerso() + " existe déjà!");
        }
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ApplicationException("Vérifier que l'enseignant n'est pas un responsable d'une Promotion, UE ou EC.");
        }
    }
}
