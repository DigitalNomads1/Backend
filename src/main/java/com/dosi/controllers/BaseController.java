package com.dosi.controllers;

import com.dosi.entities.Identifiable;
import com.dosi.exceptions.ValidationException;
import com.dosi.services.BaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
public abstract class BaseController<T extends Identifiable, K> {
    protected BaseService<T, K> service;

    /**
     * @return
     */
    @GetMapping({"/", ""})
    public List<T> getAll() {
        return service.findAll();
    }

    /**
     * @param entity
     * @param bindingResult
     * @return
     */
    @PostMapping("/")
    public T create(@Valid @RequestBody T entity, BindingResult bindingResult) {
        System.out.println(entity);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return service. create(entity);
    }

    /**
     * @param entity
     * @return
     */
    @PutMapping("/")
    public T update(@Valid @RequestBody T entity) {
        return service.update(entity);
    }


 }