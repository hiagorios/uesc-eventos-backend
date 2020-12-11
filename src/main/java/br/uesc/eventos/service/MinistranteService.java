package br.uesc.eventos.service;

import br.uesc.eventos.dto.MinistranteFormDTO;
import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.repository.MinistranteRepository;
import org.springframework.stereotype.Service;

@Service
public class MinistranteService extends BaseService<Ministrante, MinistranteRepository> {

    public MinistranteService() {
        super("Ministrante", 'o');
    }

    public Ministrante fromFormDto(MinistranteFormDTO dto) {
        return dto.generatePartialEntity();
    }

}
