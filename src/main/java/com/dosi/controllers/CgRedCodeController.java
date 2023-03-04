package com.dosi.controllers;

import com.dosi.entities.CgRefCode;
import com.dosi.repositories.CgRefCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dosi.utils.Constants.API_URL;

@RestController
@RequestMapping(API_URL +"/CgRefCodes")
public class CgRedCodeController {
    @Autowired
    CgRefCodeRepository repository;

    @GetMapping("/{RvDomain}")
    public List<CgRefCode> findByRvDomain(@PathVariable String RvDomain)
    {
        return repository.findByRvDomain(RvDomain);
    }
}
