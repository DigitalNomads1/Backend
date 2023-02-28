package com.dosi.controllers;

import com.dosi.entities.Identifiable;
import com.dosi.exceptions.ValidationException;
import com.dosi.services.BaseService;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@AllArgsConstructor

public abstract class BaseController<T extends Identifiable, K> {
    protected BaseService<T, K> service;

    @GetMapping({"/", ""})
    public List<T> getAll() {
        return service.findAll();
    }

    @PostMapping("/")
    public T create(@Valid @RequestBody T entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return service. create(entity);
    }

    @PutMapping("/")
    public T update(@Valid @RequestBody T entity) {
        return service.update(entity);
    }


 }