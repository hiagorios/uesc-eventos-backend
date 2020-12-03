package br.uesc.eventos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.uesc.eventos.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByInicioInscricoesLessThanAndFimInscricoesGreaterThan(LocalDateTime agora, LocalDateTime now);

    List<Evento> findByIdNot(Long id);

    @Query("SELECT COUNT(pe.id) FROM Evento e JOIN e.participantes pe WHERE e.id = ?1")
    Integer findQtdParticipantes(Long eventoId);
}
