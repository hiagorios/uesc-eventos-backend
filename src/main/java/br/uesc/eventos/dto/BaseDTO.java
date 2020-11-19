package br.uesc.eventos.dto;

import br.uesc.eventos.entity.BaseEntity;

public interface BaseDTO<E extends BaseEntity> {
	public E getEntity();
}
