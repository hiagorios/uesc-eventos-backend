package br.uesc.eventos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.uesc.eventos.dto.UsuarioFormDTO;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.enums.TipoUsuario;
import br.uesc.eventos.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {
	public Usuario fromFormDto(UsuarioFormDTO dto) {
        Usuario usuario = dto.generatePartialEntity();
        return usuario;
    }

    public Usuario findOrganizador() {
        List<Usuario> list = getRepository().findByTipoUsuario(TipoUsuario.ORGANIZADOR);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
