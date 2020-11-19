package br.uesc.eventos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.repository.UsuarioRepository;
import br.uesc.eventos.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, UsuarioRepository, UsuarioService> {

}
