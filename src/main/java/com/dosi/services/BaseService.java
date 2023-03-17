package com.dosi.services;

import com.dosi.entities.Identifiable;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseService<T extends Identifiable, K> {

    protected JpaRepository<T, K> repository;

    public void setRepository(JpaRepository<T, K> repository) {
        this.repository = repository;
    }

    /**
     * @return List
     */
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * @param entity
     * @return entity
     */
    public T create(T entity) {
        if(entity.getId() != null )
        {
            if (repository.existsById((K)entity.getId())) {
                throw new EntityExistsException("Entité " + entity.getId() + " existe déjà!");
            }
        }
        return repository.save(entity);
    }

    /**
     * @param id
     * @return entity
     */
    public T read(K id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "La ressource avec l'identifiant " + id + " n'a pas été trouvée."));
    }

    /**
     * @param entity
     * @return entity
     */
    public T update(T entity) {
        return repository.save(entity);
    }


    /**
     * @param id
     */
    public void delete(K id) {
        var entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "La ressource avec l'identifiant " + id + " n'a pas été trouvée.");
        }
    }
}