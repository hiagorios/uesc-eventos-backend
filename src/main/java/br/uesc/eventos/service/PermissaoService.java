package br.uesc.eventos.service;

import br.uesc.eventos.entity.Permissao;
import br.uesc.eventos.exception.CustomResponseException;
import br.uesc.eventos.repository.PermissaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PermissaoService extends BaseService<Permissao, PermissaoRepository> {

    public PermissaoService() {
        super("Permissão", 'a');
    }

    public Permissao findByKey(String key) {
        Optional<Permissao> opt = this.getRepository().findByKey(key);
        return opt.orElseThrow(() -> new CustomResponseException(HttpStatus.NOT_FOUND, "Nenhuma permissão encontrada com a chave " + key));
    }

    public boolean checkExistsByKey(String key) {
        Optional<Permissao> opt = this.getRepository().findByKey(key);
        return opt.isPresent();
    }

    public Set<Permissao> saveIfNotExistent(Set<Permissao> permissoes) {
        Set<Permissao> permissoesSalvas = new HashSet<>();
        permissoes.forEach(permissao -> {
            boolean permissaoExiste = checkExistsByKey(permissao.getKey());
            if (permissaoExiste) {
                permissoesSalvas.add(findByKey(permissao.getKey()));
            } else {
                permissoesSalvas.add(create(permissao));
            }
        });
        return permissoesSalvas;
    }
}
