package com.dosi.controllers;

import com.dosi.entities.CgRefCode;
import com.dosi.repositories.CgRefCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CgRefCodes")
@CrossOrigin(origins = "*")
public class CgRefCodeController {
    @Autowired
    CgRefCodeRepository repository;

    @GetMapping("/{RvDomain}")
    public List<CgRefCode> findByRvDomain(@PathVariable String RvDomain)
    {
        return repository.findByRvDomain(RvDomain);
    }
}
