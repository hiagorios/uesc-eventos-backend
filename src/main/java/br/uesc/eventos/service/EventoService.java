package br.uesc.eventos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.uesc.eventos.dto.EventoListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uesc.eventos.dto.EventoFormDTO;
import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.repository.EventoRepository;

@Service
public class EventoService extends BaseService<Evento, EventoRepository> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MinistranteService ministranteService;

    public List<Evento> findAllAvailable() {
        LocalDateTime agora = LocalDateTime.now();
        return getRepository().findByInicioInscricoesLessThanAndFimInscricoesGreaterThan(agora, agora);
    }

    public List<Evento> findAllExcluding(Long excludeId) {
        if (excludeId != null){
            return getRepository().findByIdNot(excludeId);
        }
        return getRepository().findAll();
    }

    public Evento fromFormDto(EventoFormDTO dto) {
        Evento evento = dto.generatePartialEntity();
        // TODO após implementar autenticação, setar o usuario logado
        evento.setOrganizador(usuarioService.findOrganizador());
        if (dto.getIdEventoPai() != null) {
            evento.setEventoPai(findById(dto.getIdEventoPai()));
        }
        dto.getIdsMinistrantes().forEach(ministranteId -> {
            evento.getMinistrantes().add(ministranteService.findById(ministranteId));
        });
        return evento;
    }

    public EventoListDTO toListDto(Evento evento) {
        EventoListDTO dto = new EventoListDTO(evento);
        Integer vagasOcupadas = getRepository().findQtdParticipantes(evento.getId());
        dto.setVagasRestantes(evento.getQtdVagas() - vagasOcupadas);
        return dto;
    }
}
