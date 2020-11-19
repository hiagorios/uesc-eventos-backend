package br.uesc.eventos.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {

	ADMINISTRADOR('A'), ORGANIZADOR('O'), PARTICIPANTE('P');

	private char charTipo;

	private TipoUsuario(char nome) {
		this.charTipo = nome;
	}

	@JsonValue
	public char getNome() {
		return charTipo;
	}
	
	
}
