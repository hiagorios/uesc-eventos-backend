package br.uesc.eventos.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Perfil extends BaseEntity {

    @NotNull
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "perfil_permissao",
            joinColumns = {@JoinColumn(name = "perfil_id")},
            inverseJoinColumns = {@JoinColumn(name = "permissao_id")}
    )
    private Set<Permissao> permissoes;

    public Perfil() {

    }

    public Perfil(@NotNull String nome, Set<Permissao> permissoes) {
        this.nome = nome;
        this.permissoes = permissoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
