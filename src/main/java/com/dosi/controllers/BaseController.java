package com.dosi.controllers;

import com.dosi.services.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

 @AllArgsConstructor
public abstract class BaseController<T, K> {
    protected BaseService<T, K> service;

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }

    @PostMapping("/")
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @PutMapping("/")
    public T update(@RequestBody T entity) {
        return service.update(entity);
    }
 }