package br.uesc.eventos.controller;

import br.uesc.eventos.dto.EventoListDTO;
import br.uesc.eventos.dto.EventoFormDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;
import br.uesc.eventos.service.EventoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController extends BaseController<Evento, EventoRepository, EventoService> {

    @GetMapping("/formDto/{id}")
    @Transactional
    @ApiOperation(value = "Buscar DTO para edição de evento")
    public ResponseEntity<EventoFormDTO> getFormDto(@PathVariable Long id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(new EventoFormDTO(evento));
    }

    @GetMapping("/listDto/{id}")
    @Transactional
    @ApiOperation(value = "Buscar DTO para visualização de evento")
    public ResponseEntity<EventoListDTO> getListDto(@PathVariable Long id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(service.toListDto(evento));
    }

    @GetMapping("/available")
    @Transactional
    @ApiOperation(value = "Buscar DTOs de eventos disponíveis")
    public ResponseEntity<List<EventoListDTO>> findAvailable() {
        return ResponseEntity.ok().body(
                service.findAllAvailable().stream().map(evento -> service.toListDto(evento)).collect(Collectors.toList()));
    }

    @GetMapping("/allDto")
    @Transactional
    @ApiOperation(value = "Buscar DTOs para todos os eventos")
    public ResponseEntity<List<EventoListDTO>> findAllDto(@RequestParam(value = "excludeId", required = false) Long excludeId) {
        List<EventoListDTO> list = service.findAllExcluding(excludeId).stream().map(evento -> service.toListDto(evento))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/storeDto")
    @Transactional
    @ApiOperation(value = "Enviar DTO para criação de evento")
    public ResponseEntity<Evento> storeDto(@RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        evento = service.create(evento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/realizarInscricao/{eventoId}/{usuarioId}")
    @Transactional
    @ApiOperation(value = "Realizar inscrição no evento")
    public ResponseEntity<Void> increver(@PathVariable Long eventoId, @PathVariable Long usuarioId){
        this.service.inscrever(eventoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateDto/{id}")
    @Transactional
    @ApiOperation(value = "Enviar DTO para atualização de evento")
    public ResponseEntity<Void> updateDto(@PathVariable Long id, @RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        service.update(id, evento);
        return ResponseEntity.noContent().build();
    }
}
