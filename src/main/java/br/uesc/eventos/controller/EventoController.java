package br.uesc.eventos.controller;

import br.uesc.eventos.dto.EventoFormDTO;
import br.uesc.eventos.dto.EventoListDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;
import br.uesc.eventos.service.EventoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController extends BaseController<Evento, EventoRepository, EventoService> {

    @GetMapping("/formDto/{id}")
    @PreAuthorize("hasAnyAuthority('CRIAR_EVENTO', 'EDITAR_EVENTO')")
    @ApiOperation(value = "Buscar DTO para edição de evento")
    public ResponseEntity<EventoFormDTO> getFormDto(@PathVariable Long id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(new EventoFormDTO(evento));
    }

    @GetMapping("/listDto/{id}")
    @PreAuthorize("hasAuthority('INSCREVER_SE')")
    @ApiOperation(value = "Buscar DTO para visualização de evento")
    public ResponseEntity<EventoListDTO> getListDto(@PathVariable Long id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(service.toListDto(evento));
    }

    @GetMapping("/available")
    @PreAuthorize("hasAuthority('INSCREVER_SE')")
    @ApiOperation(value = "Buscar DTOs de eventos disponíveis")
    public ResponseEntity<List<EventoListDTO>> findAvailable() {
        List<Evento> list = service.findAllAvailable();
        List<EventoListDTO> dtos = list.stream().map(evento -> service.toListDto(evento)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/available/{date}")
    @PreAuthorize("hasAuthority('INSCREVER_SE')")
    @ApiOperation(value = "Buscar DTOs de eventos disponíveis por data maior ou igual")
    public ResponseEntity<List<EventoListDTO>> findAvailableByDate(@PathVariable String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        List<Evento> list = service.findAllAvailable();
        List<Evento> filteredList = list.stream().filter(evento -> evento.getFimInscricoes().isEqual(dateTime) || evento.getFimInscricoes().isAfter(dateTime)).collect(Collectors.toList());
        List<EventoListDTO> dtos = filteredList.stream().map(evento -> service.toListDto(evento)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/allDto")
    @PreAuthorize("hasAnyAuthority('CONSULTAR_EVENTO', 'CRIAR_EVENTO')")
    @ApiOperation(value = "Buscar DTOs para todos os eventos")
    public ResponseEntity<List<EventoListDTO>> findAllDto(@RequestParam(value = "excludeId", required = false) Long excludeId) {
        List<EventoListDTO> list = service.findAllExcluding(excludeId).stream().map(evento -> service.toListDto(evento))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @Transactional
    @PostMapping("/storeDto")
    @PreAuthorize("hasAnyAuthority('CRIAR_EVENTO', 'EDITAR_EVENTO')")
    @ApiOperation(value = "Enviar DTO para criação de evento")
    public ResponseEntity<Evento> storeDto(@RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        evento = service.create(evento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @PostMapping("/realizarInscricao/{eventoId}/{usuarioId}")
    @PreAuthorize("hasAuthority('INSCREVER_SE')")
    @ApiOperation(value = "Realizar inscrição no evento")
    public ResponseEntity<Void> increver(@PathVariable Long eventoId, @PathVariable Long usuarioId) {
        this.service.inscrever(eventoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/updateDto/{id}")
    @PreAuthorize("hasAnyAuthority('CRIAR_EVENTO', 'EDITAR_EVENTO')")
    @ApiOperation(value = "Enviar DTO para atualização de evento")
    public ResponseEntity<Void> updateDto(@PathVariable Long id, @RequestBody EventoFormDTO dto) {
        Evento evento = service.fromFormDto(dto);
        service.update(id, evento);
        return ResponseEntity.noContent().build();
    }
}
