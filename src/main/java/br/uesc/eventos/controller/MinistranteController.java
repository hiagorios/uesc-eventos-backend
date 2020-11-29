package br.uesc.eventos.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Ministrante> storeDto(@RequestBody @Valid MinistranteFormDTO dto) {
        Ministrante ministrante = service.fromFormDto(dto);
        ministrante = service.create(ministrante);
        return ResponseEntity.status(201).body(ministrante);
    }

}
