package br.uesc.eventos.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.uesc.eventos.enums.TipoUsuario;

@Entity
public class Usuario extends BaseEntity {

    @NotNull
    private String nome;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @NotNull
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private String cpf;

    @ManyToMany
    @JoinTable(name = "participante_evento", joinColumns = @JoinColumn(name = "participante_id"), inverseJoinColumns = @JoinColumn(name = "evento_id"))
    @JsonIgnore
    Set<Evento> eventos;

    public Usuario() {

    }

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.cpf = cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

}
