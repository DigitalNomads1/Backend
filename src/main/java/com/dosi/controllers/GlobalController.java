package com.dosi.controllers;

import com.dosi.services.BaseService;
import org.springframework.web.bind.annotation.*;

public abstract class GlobalController<T, K> extends BaseController{

    public GlobalController(BaseService service) {
        super(service);
    }

    /**
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable K id) {
        service.delete(id);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public T read(@PathVariable K id) {
        return (T)service.read(id);
    }


}