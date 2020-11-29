package br.uesc.eventos.service;

import org.springframework.stereotype.Service;

import br.uesc.eventos.dto.MinistranteFormDTO;
import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.repository.MinistranteRepository;

@Service
public class MinistranteService extends BaseService<Ministrante, MinistranteRepository> {
	 public Ministrante fromFormDto(MinistranteFormDTO dto) {
	        Ministrante ministrante = dto.generatePartialEntity();
	        return ministrante;
	    }

}
