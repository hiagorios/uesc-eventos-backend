package br.uesc.eventos.dto;

import java.time.LocalDateTime;

import br.uesc.eventos.entity.Evento;

public class EventoDTO implements BaseDTO<Evento> {

    private Long id;
    private Long idEventoPai;
    private UsuarioDTO organizador;
    private String nome;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String local;
    private Double preco;
    private Integer qtdVagas;
    private LocalDateTime inicioInscricoes;
    private LocalDateTime fimInscricoes;

    public EventoDTO() {

    }

    public EventoDTO(Evento evento) {
        this.id = evento.getId();
        this.organizador = new UsuarioDTO(evento.getOrganizador());
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
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

    public String getNome() {
        return nome;
    }

    public Long getIdEventoPai() {
        return idEventoPai;
    }

    public void setIdEventoPai(Long idEventoPai) {
        this.idEventoPai = idEventoPai;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioDTO getOrganizador() {
        return organizador;
    }

    public void setOrganizador(UsuarioDTO organizador) {
        this.organizador = organizador;
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

}
