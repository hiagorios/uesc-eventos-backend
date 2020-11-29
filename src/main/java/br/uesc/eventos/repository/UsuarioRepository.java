package br.uesc.eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.enums.TipoUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByTipoUsuario(TipoUsuario tipo);

}
