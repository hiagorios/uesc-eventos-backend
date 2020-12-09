package br.uesc.eventos.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uesc.eventos.dto.MinistranteFormDTO;

import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.repository.MinistranteRepository;
import br.uesc.eventos.service.MinistranteService;

@RestController
@RequestMapping("/ministrantes")
public class MinistranteController extends BaseController<Ministrante, MinistranteRepository, MinistranteService> {
	
	@PostMapping("/storeDto")
    @Transactional
    @PreAuthorize("hasAuthority('CRIAR_MINISTRANTE')")
    @ApiOperation(value = "Enviar DTO para criação de ministrante")
    public ResponseEntity<Ministrante> storeDto(@RequestBody @Valid Ministrante ministrante) {
        ministrante = service.create(ministrante);
        return ResponseEntity.status(201).body(ministrante);
    }

}
