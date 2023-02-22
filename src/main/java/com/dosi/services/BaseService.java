package com.dosi.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"));
    }

    public T update(T entity) {
        return repository.save(entity);
    }

    public void delete(K id) {
        repository.deleteById(id);
    }
}