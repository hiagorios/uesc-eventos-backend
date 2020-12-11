package br.uesc.eventos.controller;

import br.uesc.eventos.dto.MinistranteFormDTO;
import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.repository.MinistranteRepository;
import br.uesc.eventos.service.MinistranteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/ministrantes")
public class MinistranteController extends BaseController<Ministrante, MinistranteRepository, MinistranteService> {

    @GetMapping("/formDto/{id}")
    @PreAuthorize("hasAnyAuthority('CRIAR_MINISTRANTE', 'EDITAR_MINISTRANTE')")
    @ApiOperation(value = "Buscar DTO para edição de ministrante")
    public ResponseEntity<MinistranteFormDTO> getFormDto(@PathVariable Long id) {
        Ministrante ministrante = service.findById(id);
        return ResponseEntity.ok().body(new MinistranteFormDTO(ministrante));
    }

    @PostMapping("/storeDto")
    @Transactional
    @PreAuthorize("hasAuthority('CRIAR_MINISTRANTE')")
    @ApiOperation(value = "Enviar DTO para criação de ministrante")
    public ResponseEntity<Ministrante> storeDto(@RequestBody @Valid MinistranteFormDTO dto) {
        Ministrante ministrante = service.fromFormDto(dto);
        ministrante = service.create(ministrante);
        return ResponseEntity.status(201).body(ministrante);
    }

    @Transactional
    @PutMapping("/updateDto/{id}")
    @PreAuthorize("hasAnyAuthority('CRIAR_MINISTRANTE', 'EDITAR_MINISTRANTE')")
    @ApiOperation(value = "Enviar DTO para atualização de ministrante")
    public ResponseEntity<Void> updateDto(@PathVariable Long id, @RequestBody MinistranteFormDTO dto) {
        Ministrante ministrante = service.fromFormDto(dto);
        service.update(id, ministrante);
        return ResponseEntity.noContent().build();
    }

}
