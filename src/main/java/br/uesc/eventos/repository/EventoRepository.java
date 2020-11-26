package br.uesc.eventos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uesc.eventos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByInicioInscricoesLessThanAndFimInscricoesGreaterThan(LocalDateTime agora, LocalDateTime now);
}
