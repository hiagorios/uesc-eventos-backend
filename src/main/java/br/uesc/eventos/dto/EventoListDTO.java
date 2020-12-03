package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Evento;

import java.time.LocalDateTime;

public class EventoListDTO implements BaseDTO<Evento> {

    private Long id;
    private EventoDTO eventoPai;
    private UsuarioDTO organizador;
    private String nome;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String local;
    private String imagem;
    private Double preco;
    private Integer qtdVagas;
    private Integer vagasRestantes;
    private LocalDateTime inicioInscricoes;
    private LocalDateTime fimInscricoes;

    public EventoListDTO() {

    }

    public EventoListDTO(Evento evento) {
        this.id = evento.getId();
        this.organizador = new UsuarioDTO(evento.getOrganizador());
        if (evento.getEventoPai() != null) {
            this.eventoPai = new EventoDTO(evento.getEventoPai());
        }
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.imagem = evento.getImagem();
        this.inicio = evento.getInicio();
        this.fim = evento.getFim();
        this.local = evento.getLocal();
        this.preco = evento.getPreco();
        this.qtdVagas = evento.getQtdVagas();
        this.inicioInscricoes = evento.getInicioInscricoes();
        this.fimInscricoes = evento.getFimInscricoes();
    }

    @Override
    public Evento generatePartialEntity() {
        Evento evento = new Evento();
        evento.setId(id);
        evento.setNome(nome);
        evento.setDescricao(descricao);
        evento.setInicio(inicio);
        evento.setFim(fim);
        evento.setLocal(local);
        evento.setImagem(imagem);
        evento.setPreco(preco);
        evento.setQtdVagas(qtdVagas);
        evento.setInicioInscricoes(inicioInscricoes);
        evento.setFimInscricoes(fimInscricoes);
        return evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoDTO getEventoPai() {
        return eventoPai;
    }

    public void setEventoPai(EventoDTO eventoPai) {
        this.eventoPai = eventoPai;
    }

    public UsuarioDTO getOrganizador() {
        return organizador;
    }

    public void setOrganizador(UsuarioDTO organizador) {
        this.organizador = organizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(Integer qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public LocalDateTime getInicioInscricoes() {
        return inicioInscricoes;
    }

    public void setInicioInscricoes(LocalDateTime inicioInscricoes) {
        this.inicioInscricoes = inicioInscricoes;
    }

    public LocalDateTime getFimInscricoes() {
        return fimInscricoes;
    }

    public void setFimInscricoes(LocalDateTime fimInscricoes) {
        this.fimInscricoes = fimInscricoes;
    }

    public Integer getVagasRestantes() {
        return vagasRestantes;
    }

    public void setVagasRestantes(Integer vagasRestantes) {
        this.vagasRestantes = vagasRestantes;
    }
}
