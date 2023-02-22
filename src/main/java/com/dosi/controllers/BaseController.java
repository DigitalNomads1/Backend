package com.dosi.controllers;

import com.dosi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, J> {

    private final BaseService<T, J> service;

    public BaseController(BaseService<T, J> service) {
        this.service = service;
    }

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


    @GetMapping("/{id}")
    public T read(@PathVariable J id) {
        return service.read(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable J id) {
        service.delete(id);
    }
}