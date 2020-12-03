package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Evento;

import java.time.LocalDateTime;

public class EventoDTO implements BaseDTO<Evento> {

    private Long id;
    private String nome;

    public EventoDTO() {

    }

    public EventoDTO(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
    }

    @Override
    public Evento generatePartialEntity() {
        Evento evento = new Evento();
        evento.setId(id);
        evento.setNome(nome);
        return evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
