package br.uesc.eventos.service;

import br.uesc.eventos.dto.UsuarioFormDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.exception.CustomResponseException;
import br.uesc.eventos.repository.UsuarioRepository;
import br.uesc.eventos.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {

    @Autowired
    private PerfilService perfilService;

    public UsuarioService() {
        super("Usuário", 'o');
    }

    public Usuario fromFormDto(UsuarioFormDTO dto) {
        Usuario usuario = dto.generatePartialEntity();
        if (dto.getPerfilId() != null) {
            usuario.setPerfil(perfilService.findById(dto.getPerfilId()));
        }
        return usuario;
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> opt = getRepository().findByEmail(email);
        return opt.orElseThrow(() -> new CustomResponseException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado com o email " + email));
    }

    public Usuario getUsuarioAutenticado() {
        return findByEmail(MyUserDetailsService.getAuthenticated().getUsername());
    }

    @Transactional
    public void deleteCascading(Long id) {
        Usuario usuario = findById(id);
        usuario.setEventos(null);
        update(id, usuario);
        destroy(id);
    }

}
