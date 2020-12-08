package br.uesc.eventos.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PermissaoEnum {

    CRIAR_USUARIO("Criar usuários"),
    CONSULTAR_USUARIO("Consultar usuários"),
    EDITAR_USUARIO("Editar usuários"),
    DELETAR_USUARIO("Deletar usuários"),

    CRIAR_EVENTO("Criar eventos"),
    CONSULTAR_EVENTO("Consultar eventos"),
    EDITAR_EVENTO("Editar eventos"),
    DELETAR_EVENTO("Deletar eventos"),

    CRIAR_MINISTRANTE("Criar ministrantes"),
    CONSULTAR_MINISTRANTE("Consultar ministrantes"),
    EDITAR_MINISTRANTE("Editar ministrantes"),
    DELETAR_MINISTRANTE("Deletar ministrantes"),

    INSCREVER_SE("Inscrever-se em eventos");

    private final String nome;

    PermissaoEnum(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }
}
