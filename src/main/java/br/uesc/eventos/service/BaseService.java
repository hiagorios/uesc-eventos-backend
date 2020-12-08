package br.uesc.eventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.uesc.eventos.entity.BaseEntity;

import javax.transaction.Transactional;

public abstract class BaseService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

	@Autowired
	private R repository;
	
	protected R getRepository() {
	    return repository;
	}

	public List<E> findAll() {
		return getRepository().findAll();
	}
	
	public E findById(Long id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não foi encontrada!");
        }
		return optional.get();
    }

	@Transactional
	public E create(E entity) {
		return getRepository().save(entity);
	}

	@Transactional
	public E update(Long id, E entity) {
		this.findById(id);
		entity.setId(id);
		entity = getRepository().save(entity);
		return entity;
	}

	@Transactional
	public void destroy(Long id) {
		E entity = this.findById(id);
		getRepository().delete(entity);
	}
}
