package br.uesc.eventos.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.uesc.eventos.dto.EventoDTO;
import br.uesc.eventos.dto.EventoFormDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;
import br.uesc.eventos.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController extends BaseController<Evento, EventoRepository, EventoService> {

    @PostMapping("/storeDto")
    @Transactional
    public ResponseEntity<Evento> storeDto(@RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        evento = service.create(evento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/formDto/{id}")
    @Transactional
    public ResponseEntity<EventoFormDTO> getFormDto(@PathVariable Long id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(new EventoFormDTO(evento));
    }

    @PutMapping("/updateDto/{id}")
    @Transactional
    public ResponseEntity<Evento> updateDto(@PathVariable Long id, @RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        service.update(id, evento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    @Transactional
    public ResponseEntity<List<EventoDTO>> findAvailable() {
        return ResponseEntity.ok().body(
                service.findAllAvailable().stream().map(evento -> new EventoDTO(evento)).collect(Collectors.toList()));
    }

    @GetMapping("/allDto")
    @Transactional
    public ResponseEntity<List<EventoDTO>> findAllDto(@RequestParam(value = "excludeId", required = false) Long id) {
        List<EventoDTO> list = service.findAll().stream().map(evento -> new EventoDTO(evento))
                .collect(Collectors.toList());
        if (id != null) {
            list = list.stream().filter(evento -> evento.getId() != id).collect(Collectors.toList());
        }
        return ResponseEntity.ok().body(list);
    }
}
