package com.dosi.promotion.services;

import com.dosi.controllers.BaseController;
import com.dosi.entities.Candidat;
import com.dosi.repositories.CandidatRepository;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 @Service
public class CandidatService extends BaseService {
     public CandidatService(CandidatRepository candidatRepository) {
         super(candidatRepository);
     }
}