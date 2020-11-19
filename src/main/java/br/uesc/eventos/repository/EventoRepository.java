package br.uesc.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uesc.eventos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
