package br.uesc.eventos.service;

import br.uesc.eventos.entity.Perfil;
import br.uesc.eventos.exception.CustomResponseException;
import br.uesc.eventos.repository.PerfilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService extends BaseService<Perfil, PerfilRepository> {

    public Perfil findByNome(String nome) {
        Optional<Perfil> opt = this.getRepository().findByNome(nome);
        return opt.orElseThrow(() -> new CustomResponseException(HttpStatus.NOT_FOUND, "Nenhum perfil encontrado com o nome " + nome));
    }
}
