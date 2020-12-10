package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Perfil;

public class PerfilDTO implements BaseDTO<Perfil> {

    private Long id;
    private String nome;

    public PerfilDTO() {

    }

    public PerfilDTO(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
    }

    @Override
    public Perfil generatePartialEntity() {
        Perfil perfil = new Perfil();
        perfil.setId(id);
        perfil.setNome(nome);
        return perfil;
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
