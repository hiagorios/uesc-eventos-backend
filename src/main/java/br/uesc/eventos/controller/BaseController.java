package br.uesc.eventos.controller;

import br.uesc.eventos.entity.BaseEntity;
import br.uesc.eventos.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public abstract class BaseController<E extends BaseEntity, R extends JpaRepository<E, Long>, S extends BaseService<E, R>> {

    @Autowired
    protected S service;

    @GetMapping
    @ApiOperation(value = "Consultar todos os registros")
    public ResponseEntity<List<E>> findAll() {
        List<E> entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }

    @PostMapping
    @ApiOperation(value = "Criar um registro")
    public ResponseEntity<E> create(@RequestBody @Valid E entity) {
        entity = service.create(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um registro por id")
    public ResponseEntity<E> findById(@PathVariable(value = "id") Long id) {
        E entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Editar um registro")
    public ResponseEntity<E> update(@PathVariable(value = "id") Long id, @RequestBody @Valid E entity) {
        service.update(id, entity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover um registro")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.destroy(id);
        return ResponseEntity.noContent().build();
    }

}
