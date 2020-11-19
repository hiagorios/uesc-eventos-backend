package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Evento;

public class EventoCreateDTO implements BaseDTO<Evento>{

    @Override
    public Evento getEntity() {
        return new Evento();
    }
    
}
