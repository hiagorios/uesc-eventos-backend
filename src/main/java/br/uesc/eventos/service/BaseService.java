package br.uesc.eventos.service;

import br.uesc.eventos.entity.BaseEntity;
import br.uesc.eventos.exception.CustomResponseException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    @Autowired
    private R repository;

    private final String entityName;
    private final char wordGenderLetter;
    private final String theEntity;

    public BaseService(String entityName, char wordGenderLetter) {
        this.entityName = entityName;
        this.wordGenderLetter = wordGenderLetter;
        this.theEntity = wordGenderLetter + " " + entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public char getWordGenderLetter() {
        return wordGenderLetter;
    }

    public String getTheEntity() {
        return theEntity;
    }

    protected R getRepository() {
        return repository;
    }

    public List<E> findAll() {
        return getRepository().findAll();
    }

    public E findById(Long id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            throw new CustomResponseException(HttpStatus.NOT_FOUND, getEntityName() + " não encontrad" + getWordGenderLetter());
        }
        return optional.get();
    }

    public E create(E entity) {
        try {
            return getRepository().save(entity);
        } catch (IllegalArgumentException e) {
            throw new CustomResponseException(HttpStatus.BAD_REQUEST, "Não foi possível criar. " + getEntityName() + " inválid" + getWordGenderLetter());
        }
    }

    public E update(Long id, E entity) {
        this.findById(id);
        entity.setId(id);
        try {
            entity = getRepository().save(entity);
            return entity;
        } catch (IllegalArgumentException e) {
            throw new CustomResponseException(HttpStatus.BAD_REQUEST, "Não foi possível editar. " + getEntityName() + " inválid" + getWordGenderLetter());
        }
    }

    public void destroy(Long id) {
        E entity = this.findById(id);
        try {
            getRepository().delete(entity);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            throw new CustomResponseException(HttpStatus.CONFLICT, "Não foi possível deletar " + getTheEntity() + ", pois está associado a outros objetos.");
        }
    }
}
