package com.dosi.services;

import com.dosi.entities.Rubrique;
import com.dosi.repositories.QuestionRepository;
import com.dosi.repositories.RubriqueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RubriqueService extends BaseService<Rubrique, Integer> {

    public RubriqueService(RubriqueRepository rubriqueRepository) {
        super(rubriqueRepository);
    }


    public Rubrique getRubriqueByDesignation(String designation) {
        return ((RubriqueRepository)repository).findByDesignation(designation);
    }

    public List<Map> getAllAvgOfRubriques(Integer id_eval) {
        System.out.println(((RubriqueRepository)repository).findAvgOfEveryRubrique(id_eval));
        List<Map> list = new ArrayList<Map>();
        ((RubriqueRepository)repository).findAvgOfEveryRubrique(id_eval).forEach( question ->
                {
                    Map map = new HashMap();
                    map.put("id_rubrique", question.get(0));
                    map.put("moyenne", question.get(1));
                    list.add(map);
                }
        );
        return list;
    }
}
