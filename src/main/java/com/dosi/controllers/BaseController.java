package com.dosi.controllers;

import com.dosi.entities.Identifiable;
import com.dosi.services.BaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }

    @PostMapping("/")
    public T create(@Valid @RequestBody T entity) {
                return service. create(entity);
    }

    @PutMapping("/")
    public T update(@Valid @RequestBody T entity) {
        return service.update(entity);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println("*******************");
        System.out.println( ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

 }