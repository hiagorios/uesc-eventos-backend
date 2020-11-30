package br.uesc.eventos.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uesc.eventos.entity.Evento;
import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.entity.Usuario;
import br.uesc.eventos.enums.TipoUsuario;

@Service
public class StartupService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private MinistranteService ministranteService;

    public void instantiateDevDatabase() throws Exception {
        Usuario u1 = new Usuario("Hiago Rios", "hrcordeiro.cic@uesc.br", "123456", TipoUsuario.ADMINISTRADOR,
                "73856832076");
        u1 = usuarioService.create(u1);
        Usuario u2 = new Usuario("Joao Henrique", "jhalgumacoisa.cic@uesc.br", "123456", TipoUsuario.ORGANIZADOR,
                "94924483001");
        u2 = usuarioService.create(u2);
        Usuario u3 = new Usuario("Allana Campos", "acagumacoisa.cic@uesc.br", "123456", TipoUsuario.PARTICIPANTE,
                "49146701001");
        u3 = usuarioService.create(u3);
        Usuario u4 = new Usuario("Daniel Penedo", "dpalgumacoisa.cic@uesc.br", "123456", TipoUsuario.PARTICIPANTE,
                "31876323000");
        u4 = usuarioService.create(u4);

        Ministrante m1 = new Ministrante("Paulo Silveira", "paulosilveira@alura.com", "Brabo apenas");
        m1 = ministranteService.create(m1);
        Ministrante m2 = new Ministrante("Elon Musk", "elinndazn@tesla.com", "Rico apenas");
        m2 = ministranteService.create(m2);
        Ministrante m3 = new Ministrante("Diogo Defante", "diogo@defante.com", "Diogo Defante");
        m3 = ministranteService.create(m3);
        Ministrante m4 = new Ministrante("Evaristo Costa", "evaristo@globo.com",
                "Formado em Jornalismo pela Universidade Braz Cubas, de Mogi das Cruzes, ingressou na profissão em 1995, quando ainda estava na faculdade.");
        m4 = ministranteService.create(m4);
        Ministrante m5 = new Ministrante("Richard Rasmussen", "richard@pororoca.com", "Aventura selvagem");
        m5 = ministranteService.create(m5);

        LocalDateTime now = LocalDateTime.now();

        Evento e1 = new Evento(u2, "Campeonato de CS", "Vai ser foda", "Pav. de Ciências Exatas e Tecnológicas",
                Double.valueOf(20d), 30, now.plusDays(5).plusHours(12), now.plusDays(5).plusHours(17),
                now.minusDays(5).minusHours(12), now.plusHours(12), null);
        e1.setMinistrantes(new HashSet<>(Arrays.asList(m1, m2)));
        e1 = eventoService.create(e1);

        Evento e2 = new Evento(u2, "Campeonato de Pebolim", "Quem perder passa debaixo da mesa", "Céu",
                Double.valueOf(20d), 30, now.plusHours(1), now.plusHours(4), now.minusDays(5), now.plusMinutes(5),
                e1);
        e2.setMinistrantes(new HashSet<>(Arrays.asList(m3)));
        e2 = eventoService.create(e2);

        Evento e3 = new Evento(u2, "Palestra Animais Exóticos", "Com o brabo da selva", "Auditório Pav. Jorge Amado",
                Double.valueOf(20d), 30, now.plusDays(2), now.plusDays(2).plusHours(6), now.minusDays(10),
                now.plusHours(2), null);
        e3.setMinistrantes(new HashSet<>(Arrays.asList(m5)));
        e3 = eventoService.create(e3);

        Evento e4 = new Evento(u2, "Debate de eleições do DCE", "Todos os discentes estão convidados",
                "Pav. Max de Menezes", Double.valueOf(20d), 30, now.plusDays(4), now.plusDays(4).plusHours(3),
                now.minusDays(4), now.minusDays(1), null);
        e4.setMinistrantes(new HashSet<>(Arrays.asList(m4)));
        e4 = eventoService.create(e4);

        u1.setEventos(new HashSet<>(Arrays.asList(e1, e2, e3)));
        u2.setEventos(new HashSet<>(Arrays.asList(e1, e3)));
        u3.setEventos(new HashSet<>(Arrays.asList(e2, e3)));
        u4.setEventos(new HashSet<>(Arrays.asList(e2, e3, e4)));

    }

    public void instantiateTestDatabase() throws Exception {

    }
}
