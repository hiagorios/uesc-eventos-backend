package br.uesc.eventos.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Evento extends BaseEntity {

    @ManyToOne
    private Evento eventoPai;

    @OneToMany(mappedBy = "eventoPai")
    @JsonIgnore
    private Set<Evento> eventosFilhos;

    @NotNull
    private String nome;

    @NotNull
    @Lob
    private String descricao;

    @NotNull
    private String local;

    @NotNull
    private Double preco;

    @NotNull
    private Integer qtdVagas;

    @Lob
    private String imagem;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    private LocalDateTime inicioInscricoes;

    private LocalDateTime fimInscricoes;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id", name = "organizador_id", updatable = false)
    private Usuario organizador;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ministrante_evento", joinColumns = @JoinColumn(name = "evento_id"), inverseJoinColumns = @JoinColumn(name = "ministrante_id"))
    private Set<Ministrante> ministrantes;

    @ManyToMany(mappedBy = "eventos")
    private Set<Usuario> participantes;

    public Evento() {

    }

    public Evento(Usuario organizador, String nome, String descricao, String local, Double preco, Integer qtdVagas,
            LocalDateTime inicio, LocalDateTime fim, LocalDateTime inicioInscricoes, LocalDateTime fimInscricoes,
            Evento eventoPai) {
        this.eventoPai = eventoPai;
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.preco = preco;
        this.qtdVagas = qtdVagas;
        this.inicio = inicio;
        this.fim = fim;
        this.inicioInscricoes = inicioInscricoes;
        this.fimInscricoes = fimInscricoes;
        this.organizador = organizador;
    }

    public Evento getEventoPai() {
        return eventoPai;
    }

    public void setEventoPai(Evento eventoPai) {
        this.eventoPai = eventoPai;
    }

    public Set<Evento> getEventosFilhos() {
        if (this.eventosFilhos == null) {
            this.eventosFilhos = new HashSet<>();
        }
        return eventosFilhos;
    }

    public void setEventosFilhos(Set<Evento> eventosFilhos) {
        this.eventosFilhos = eventosFilhos;
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

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public Set<Ministrante> getMinistrantes() {
        if (this.ministrantes == null) {
            this.ministrantes = new HashSet<>();
        }
        return ministrantes;
    }

    public void setMinistrantes(Set<Ministrante> ministrantes) {
        this.ministrantes = ministrantes;
    }

    public Set<Usuario> getParticipantes() {
        if (this.participantes == null) {
            this.participantes = new HashSet<>();
        }
        return participantes;
    }

    public void setParticipantes(Set<Usuario> participantes) {
        this.participantes = participantes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
