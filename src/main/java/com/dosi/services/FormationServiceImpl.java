package com.dosi.services;

import com.dosi.entities.Formation;
import com.dosi.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FormationServiceImpl implements FormationService {

    @Autowired
    FormationRepository formationRepository;

    @Override
    public List<Formation> getAllFormation() {
        return formationRepository.findAll();
    }

    @Override
    public Formation findByName(String nom) {
        return formationRepository.findFormationByNomFormation(nom);
    }

    @Override
    public Optional<Formation> findById(String id) {
        return formationRepository.findById(id);
    }

    @Override
    public void createFormation(Formation formation) {
          formationRepository.save(formation);
    }

    @Override
    public void updateFormation(Formation formation) {
        formationRepository.save(formation);
    }

    @Override
    public void deleteFormation(String id) {
        formationRepository.deleteById(id);
    }
}
