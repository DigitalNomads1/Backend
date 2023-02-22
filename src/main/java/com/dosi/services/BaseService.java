package com.dosi.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService<T, J> {

    private JpaRepository<T, J> repository;

    public void setRepository(JpaRepository<T, J> repository) {
        this.repository = repository;
    }

    public List<T> findAll() { return repository.findAll();}

    public T create(T entity) {
        return repository.save(entity);
    }


    public T update(T entity) {
        return repository.save(entity);
    }
/*
    public T read(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }*/
}