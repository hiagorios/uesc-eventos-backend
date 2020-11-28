package br.uesc.eventos.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uesc.eventos.dto.EventoFormDTO;
import br.uesc.eventos.dto.EventoDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;
import br.uesc.eventos.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController extends BaseController<Evento, EventoRepository, EventoService> {

    @PostMapping("/storeDto")
    @Transactional
    public ResponseEntity<Evento> storeDto(@RequestBody @Valid EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        evento = service.create(evento);
        return ResponseEntity.status(201).body(evento);
    }
    
    @GetMapping("/available")
    @Transactional
    public ResponseEntity<List<EventoDTO>> findAvailable() {
        return ResponseEntity
                .ok(service.findAllAvailable().stream().map(evento -> new EventoDTO(evento)).collect(Collectors.toList()));
    }

    @GetMapping("/findAllDto")
    @Transactional
    public ResponseEntity<List<EventoDTO>> findAllDto() {
        return ResponseEntity
                .ok(service.findAll().stream().map(evento -> new EventoDTO(evento)).collect(Collectors.toList()));
    }
}
