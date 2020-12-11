package br.uesc.eventos.service;

import br.uesc.eventos.dto.MinistranteFormDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.repository.MinistranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MinistranteService extends BaseService<Ministrante, MinistranteRepository> {

    @Autowired
    private EventoService eventoService;

    public MinistranteService() {
        super("Ministrante", 'o');
    }

    public Ministrante fromFormDto(MinistranteFormDTO dto) {
        return dto.generatePartialEntity();
    }

    @Transactional
    public void deleteCascading(Long id) {
        Ministrante ministrante = findById(id);
        for (Evento e : ministrante.getEventos()){
            e.getMinistrantes().remove(ministrante);
            eventoService.update(e.getId(), e);
        }
        update(id, ministrante);
        destroy(id);
    }
}
