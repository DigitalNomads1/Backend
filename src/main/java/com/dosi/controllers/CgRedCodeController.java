package com.dosi.controllers;

import com.dosi.entities.CgRefCode;
import com.dosi.repositories.CgRefCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CgRefCodes")
public class CgRedCodeController{
    @Autowired
    CgRefCodeRepository repository;

    @GetMapping("/{RvDomain}")
    public List<CgRefCode> findByRvDomain(@PathVariable String RvDomain)
    {
        return repository.findByRvDomain(RvDomain);
    }
}
