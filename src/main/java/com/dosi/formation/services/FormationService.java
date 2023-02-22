package com.dosi.formation.services;

import com.dosi.entities.Formation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface FormationService {

    List<Formation> getAllFormation();

    Formation findByName(String nom);

    Optional<Formation> findById(String id);

    void createFormation(Formation formation);

    void updateFormation(Formation formation);

    void deleteFormation(String id);


}
