package br.uesc.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uesc.eventos.entity.Ministrante;

@Repository
public interface MinistranteRepository extends JpaRepository<Ministrante, Long> {

}
