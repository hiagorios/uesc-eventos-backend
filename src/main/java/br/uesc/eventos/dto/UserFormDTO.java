package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Usuario;

public class UserFormDTO implements BaseDTO<Usuario> {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    
    
    public UserFormDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    @Override
    public Usuario generatePartialEntity() {
        Usuario usuario = new Usuario();
        usuario.setSenha(senha);
        usuario.setEmail(email);
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

}
