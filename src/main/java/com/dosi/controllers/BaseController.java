package com.dosi.controllers;

import com.dosi.services.BaseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}")
    public T read(@PathVariable K id) {
        return service.read(id);
    }

    @PutMapping("/")
    public T update(@RequestBody T entity) {
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable K id) {
        service.delete(id);
    }
}