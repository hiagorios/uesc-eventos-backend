package br.uesc.eventos.repository;

import br.uesc.eventos.entity.Perfil;
import br.uesc.eventos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByPerfil(Perfil perfil);

    Optional<Usuario> findByEmail(String email);
}
