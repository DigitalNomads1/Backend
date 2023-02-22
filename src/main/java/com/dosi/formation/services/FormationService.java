package com.dosi.formation.services;

import com.dosi.formation.entities.Formation;

import java.util.List;

public interface FormationService {

    List<Formation> getAllFormation();

    Formation findByName(String nom);

    Formation findById(Integer id);

    void createFormation(Formation formation);

    void updateFormation(Formation formation);

    void deleteFormation(Integer id);


}
