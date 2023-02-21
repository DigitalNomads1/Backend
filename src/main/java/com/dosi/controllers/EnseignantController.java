package com.dosi.controllers;

import com.dosi.entities.Enseignant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController extends BaseController<Enseignant> {


}


