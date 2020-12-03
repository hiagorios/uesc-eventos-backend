package br.uesc.eventos.controller;

import br.uesc.eventos.dto.EventoListDTO;
import br.uesc.eventos.dto.EventoFormDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;
import br.uesc.eventos.service.EventoService;
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
    public ResponseEntity<EventoFormDTO> getFormDto(@PathVariable Long id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(new EventoFormDTO(evento));
    }

    @GetMapping("/available")
    @Transactional
    public ResponseEntity<List<EventoListDTO>> findAvailable() {
        return ResponseEntity.ok().body(
                service.findAllAvailable().stream().map(EventoListDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/allDto")
    @Transactional
    public ResponseEntity<List<EventoListDTO>> findAllDto(@RequestParam(value = "excludeId", required = false) Long id) {
        List<EventoListDTO> list = service.findAll().stream().map(EventoListDTO::new)
                .collect(Collectors.toList());
        if (id != null) {
            list = list.stream().filter(evento -> evento.getId() != id).collect(Collectors.toList());
        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/storeDto")
    @Transactional
    public ResponseEntity<Evento> storeDto(@RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        evento = service.create(evento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/updateDto/{id}")
    @Transactional
    public ResponseEntity<Evento> updateDto(@PathVariable Long id, @RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        service.update(id, evento);
        return ResponseEntity.noContent().build();
    }
}
