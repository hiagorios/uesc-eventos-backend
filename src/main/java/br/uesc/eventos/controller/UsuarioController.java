package br.uesc.eventos.controller;

import br.uesc.eventos.dto.PerfilDTO;
import br.uesc.eventos.dto.UsuarioDTO;
import br.uesc.eventos.dto.UsuarioFormDTO;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.security.util.PasswordUtil;
import br.uesc.eventos.service.PerfilService;
import br.uesc.eventos.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    @PreAuthorize("hasAuthority('CONSULTAR_USUARIO')")
    @ApiOperation(value = "Consultar todos os usuários")
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = service.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/allDto")
    @PreAuthorize("hasAuthority('CONSULTAR_USUARIO')")
    @ApiOperation(value = "Buscar DTOs para todos os eventos")
    public ResponseEntity<List<UsuarioDTO>> findAllDto() {
        List<UsuarioDTO> list = service.findAll().stream().map(UsuarioDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/formDto/{id}")
    @PreAuthorize("hasAuthority('EDITAR_USUARIO')")
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
        return ResponseEntity.ok().body(usuario);
    }

    @Transactional
    @PutMapping("/updateDto/{id}")
    @PreAuthorize("hasAuthority('EDITAR_USUARIO')")
    @ApiOperation(value = "Enviar DTO para atualização de usuario")
    public ResponseEntity<Void> updateDto(@PathVariable Long id, @RequestBody UsuarioFormDTO dto) {
        Usuario usuario = service.fromFormDto(dto);
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            Usuario old = service.findById(id);
            usuario.setSenha(old.getSenha());
        }
        service.update(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perfisDto")
    @PreAuthorize("hasAuthority('EDITAR_USUARIO')")
    @ApiOperation(value = "Buscar perfis para edição de usuário")
    public ResponseEntity<List<PerfilDTO>> getPerfisDto() {
        return ResponseEntity.ok().body(perfilService.findAll().stream().map(PerfilDTO::new).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETAR_USUARIO')")
    @ApiOperation(value = "Remover um usuário")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.deleteCascading(id);
        return ResponseEntity.noContent().build();
    }
}
