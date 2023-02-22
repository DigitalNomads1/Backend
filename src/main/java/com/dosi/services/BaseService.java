package com.dosi.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
public abstract class BaseService<T, K> {

    protected JpaRepository<T, K> repository;

    public void setRepository(JpaRepository<T, K> repository) {
        this.repository = repository;
    }

    public List<T> findAll() { return repository.findAll();}

    public T create(T entity) {
        return repository.save(entity);
    }

    public T read(K id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    public T update(T entity) {
        return repository.save(entity);
    }

    public void delete(K id) {
        repository.deleteById(id);
    }
}