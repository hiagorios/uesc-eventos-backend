package br.uesc.eventos.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.uesc.eventos.entity.Evento;

public class EventoFormDTO implements BaseDTO<Evento> {

    private Long id;
    private Long idEventoPai;
    private Long idOrganizador;
    private String nome;
    private String descricao;
    private String local;
    private String imagem;
    private Double preco;
    private Integer qtdVagas;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private LocalDateTime inicioInscricoes;
    private LocalDateTime fimInscricoes;
    private List<Long> idsMinistrantes;

    @Override
    public Evento generatePartialEntity() {
        Evento evento = new Evento();
        evento.setId(id);
        evento.setNome(nome);
        evento.setDescricao(descricao);
        evento.setImagem(imagem);
        evento.setLocal(local);
        evento.setPreco(preco);
        evento.setQtdVagas(qtdVagas);
        evento.setInicio(inicio);
        evento.setFim(fim);
        evento.setInicioInscricoes(inicioInscricoes);
        evento.setFimInscricoes(fimInscricoes);
        return evento;
    }

    public EventoFormDTO() {

    }

    public EventoFormDTO(Evento evento) {
        if (evento.getEventoPai() != null) {
            this.idEventoPai = evento.getEventoPai().getId();
        }
        this.id = evento.getId();
        this.idOrganizador = evento.getOrganizador().getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.imagem = evento.getImagem();
        this.local = evento.getLocal();
        this.preco = evento.getPreco();
        this.qtdVagas = evento.getQtdVagas();
        this.inicio = evento.getInicio();
        this.fim = evento.getFim();
        this.inicioInscricoes = evento.getInicioInscricoes();
        this.fimInscricoes = evento.getFimInscricoes();
        evento.getMinistrantes().forEach(ministrante -> {
            getIdsMinistrantes().add(ministrante.getId());
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdsMinistrantes() {
        if(this.idsMinistrantes == null) {
            idsMinistrantes = new ArrayList<>();
        }
        return idsMinistrantes;
    }

    public void setIdsMinistrantes(List<Long> idsMinistrantes) {
        this.idsMinistrantes = idsMinistrantes;
    }

    public Long getIdEventoPai() {
        return idEventoPai;
    }

    public void setIdEventoPai(Long idEventoPai) {
        this.idEventoPai = idEventoPai;
    }

    public Long getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(Long idOrganizador) {
        this.idOrganizador = idOrganizador;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
