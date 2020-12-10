package br.uesc.eventos.controller;

import br.uesc.eventos.dto.UsuarioFormDTO;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.repository.UsuarioRepository;
import br.uesc.eventos.security.util.PasswordUtil;
import br.uesc.eventos.service.PerfilService;
import br.uesc.eventos.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, UsuarioRepository, UsuarioService> {

    @Autowired
    private PerfilService perfilService;
   
    @GetMapping("/formDto/{id}")
    @Transactional
    @ApiOperation(value = "Buscar DTO para edição de usuário")
    public ResponseEntity<UsuarioFormDTO> getFormDto(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(new UsuarioFormDTO(usuario));
    }
    
    @PostMapping("/storeDto")
    @ApiOperation(value = "Enviar DTO para criação de usuário")
    public ResponseEntity<Usuario> storeDto(@RequestBody @Valid UsuarioFormDTO dto) {
        Usuario usuario = service.fromFormDto(dto);
        usuario.setPerfil(perfilService.findByNome("Participante"));
        usuario.setAtivo(true);
        usuario.setSenha(PasswordUtil.encode(usuario.getSenha()));
        usuario = service.create(usuario);
        return ResponseEntity.status(201).body(usuario);
    }
}
