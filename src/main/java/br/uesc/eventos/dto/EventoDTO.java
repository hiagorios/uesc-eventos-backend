package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Evento;

public class EventoDTO implements BaseDTO<Evento> {

    public EventoDTO() {

    }

    public EventoDTO(Evento evento) {
        
    }

    @Override
    public Evento getEntity() {
        Evento evento = new Evento();
        return evento;
    }

}
