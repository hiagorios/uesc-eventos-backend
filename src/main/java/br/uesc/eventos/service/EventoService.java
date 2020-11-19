package br.uesc.eventos.service;

import org.springframework.stereotype.Service;

import br.uesc.eventos.dto.EventoCreateDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;

@Service
public class EventoService extends BaseService<Evento, EventoRepository> {

    public Evento fromDto(EventoCreateDTO dto) {
        Evento evento = dto.getEntity();
        return evento;
    }
}
