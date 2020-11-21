package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Usuario;

public class UsuarioDTO implements BaseDTO<Usuario> {

    private Long id;
    private String nome;

    public UsuarioDTO() {
        
    }
    
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }

    @Override
    public Usuario generatePartialEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setId(id);
        return usuario;
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
