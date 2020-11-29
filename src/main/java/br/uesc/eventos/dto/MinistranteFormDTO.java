package br.uesc.eventos.dto;

import br.uesc.eventos.entity.Ministrante;

public class MinistranteFormDTO implements BaseDTO<Ministrante> {

    private Long id;
    private String nome;
    private String email;
    private String formacao;
    
    
    public MinistranteFormDTO(Ministrante ministrante) {
        this.id = ministrante.getId();
        this.nome = ministrante.getNome();
        this.email = ministrante.getEmail();
        this.formacao = ministrante.getFormacao();
    }

    @Override
    public Ministrante generatePartialEntity() {
        Ministrante ministrante = new Ministrante();
        ministrante.setFormacao(formacao);
        ministrante.setEmail(email);
        ministrante.setNome(nome);
        ministrante.setId(id);
        return ministrante;
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
    
    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

}
