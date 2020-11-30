package br.uesc.eventos.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.uesc.eventos.entity.BaseEntity;
import br.uesc.eventos.service.BaseService;
import javassist.NotFoundException;

public abstract class BaseController<E extends BaseEntity, R extends JpaRepository<E, Long>, S extends BaseService<E, R>> {

	@Autowired
    protected S service;

	@GetMapping
	public ResponseEntity<List<E>> findAll() {
		List<E> entities = service.findAll();
		return ResponseEntity.ok().body(entities);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<E> create(@RequestBody @Valid E entity) {
		entity = service.create(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<E> findById(@PathVariable(value = "id") Long id) throws NotFoundException {
		E entity = service.findById(id);
		return ResponseEntity.ok().body(entity);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<E> update(@PathVariable(value = "id") Long id, @RequestBody @Valid E entity) {
		service.update(id, entity);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
		service.destroy(id);
		return ResponseEntity.noContent().build();
	}

}
