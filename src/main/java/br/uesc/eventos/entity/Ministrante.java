package br.uesc.eventos.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Ministrante extends BaseEntity {

    @NotNull
    private String nome;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Lob
    private String formacao;

    @ManyToMany(mappedBy = "ministrantes")
    @JsonIgnore
    Set<Evento> eventos;

    public Ministrante() {

    }

    public Ministrante(String nome, String email, String formacao) {
        super();
        this.nome = nome;
        this.email = email;
        this.formacao = formacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Set<Evento> getEventos() {
        if (this.eventos == null) {
            this.eventos = new HashSet<>();
        }
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

}
