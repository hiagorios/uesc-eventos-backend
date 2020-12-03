package br.uesc.eventos.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uesc.eventos.dto.UsuarioFormDTO;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.enums.TipoUsuario;
import br.uesc.eventos.repository.UsuarioRepository;
import br.uesc.eventos.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, UsuarioRepository, UsuarioService> {

    @PostMapping("/storeDto")
    @Transactional
    public ResponseEntity<Usuario> storeDto(@RequestBody @Valid UsuarioFormDTO dto) {
        Usuario usuario = service.fromFormDto(dto);
        usuario.setTipoUsuario(TipoUsuario.PARTICIPANTE);
        usuario = service.create(usuario);
        return ResponseEntity.status(201).body(usuario);
    }
}
