package com.dosi.controllers;

import com.dosi.entities.CgRefCode;
import com.dosi.repositories.CgRefCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL + "/CgRefCodes")
public class CgRefCodeController{
    @Autowired
    CgRefCodeRepository repository;

    /**
     * @param RvDomain
     * @return
     */
    @GetMapping("/{RvDomain}")
    public List<CgRefCode> findByRvDomain(@PathVariable String RvDomain)
    {
        System.out.println(RvDomain);
        return repository.findByRvDomain(RvDomain);
    }
}
