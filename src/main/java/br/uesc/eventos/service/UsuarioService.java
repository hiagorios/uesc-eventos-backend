package br.uesc.eventos.service;

import org.springframework.stereotype.Service;

import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {

}
