package br.uesc.eventos.service;

import br.uesc.eventos.entity.*;
import br.uesc.eventos.enums.PermissaoEnum;
import br.uesc.eventos.security.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StartupService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private MinistranteService ministranteService;

    @Transactional
    public void instantiateDevDatabase() {
        Set<Permissao> todasPermissoes = criarTodasPermissoes();
        Perfil administrador = new Perfil("Administrador", todasPermissoes);
        Perfil organizador = new Perfil("Organizador", getPermissoesOrganizador(todasPermissoes));
        Perfil participante = new Perfil("Participante", getPermissoesParticipante(todasPermissoes));

        administrador = perfilService.create(administrador);
        organizador = perfilService.create(organizador);
        participante = perfilService.create(participante);

        Usuario u1 = new Usuario("Admin", "admin@uesc.br", PasswordUtil.encode("123456"), administrador,
                "73856832076");
        u1 = usuarioService.create(u1);
        Usuario u2 = new Usuario("Joao Henrique", "jhalgumacoisa.cic@uesc.br", PasswordUtil.encode("123456"), organizador,
                "94924483001");
        u2 = usuarioService.create(u2);
        Usuario u3 = new Usuario("Allana Campos", "acagumacoisa.cic@uesc.br", PasswordUtil.encode("123456"), participante,
                "49146701001");
        u3 = usuarioService.create(u3);
        Usuario u4 = new Usuario("Daniel Penedo", "dpalgumacoisa.cic@uesc.br", PasswordUtil.encode("123456"), participante,
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
                20d, 30, now.plusDays(5).plusHours(12), now.plusDays(5).plusHours(17),
                now.minusDays(5).minusHours(12), now.plusHours(12), null);
        e1.setMinistrantes(new HashSet<>(Arrays.asList(m1, m2)));
        e1 = eventoService.create(e1);

        Evento e2 = new Evento(u2, "Campeonato de Pebolim", "Quem perder passa debaixo da mesa", "Céu",
                20d, 30, now.plusHours(1), now.plusHours(4), now.minusDays(5), now.plusMinutes(5),
                e1);
        e2.setMinistrantes(new HashSet<>(Collections.singletonList(m3)));
        e2 = eventoService.create(e2);

        Evento e3 = new Evento(u2, "Palestra Animais Exóticos", "Com o brabo da selva", "Auditório Pav. Jorge Amado",
                20d, 30, now.plusDays(2), now.plusDays(2).plusHours(6), now.minusDays(10),
                now.plusHours(2), null);
        e3.setMinistrantes(new HashSet<>(Collections.singletonList(m5)));
        e3 = eventoService.create(e3);

        Evento e4 = new Evento(u2, "Debate de eleições do DCE", "Todos os discentes estão convidados",
                "Pav. Max de Menezes", 20d, 30, now.plusDays(4), now.plusDays(4).plusHours(3),
                now.minusDays(4), now.minusDays(1), null);
        e4.setMinistrantes(new HashSet<>(Collections.singletonList(m4)));
        e4 = eventoService.create(e4);

        u1.setEventos(new HashSet<>(Arrays.asList(e1, e2, e3)));
        u2.setEventos(new HashSet<>(Arrays.asList(e1, e3)));
        u3.setEventos(new HashSet<>(Arrays.asList(e2, e3)));
        u4.setEventos(new HashSet<>(Arrays.asList(e2, e3, e4)));

        usuarioService.update(u1.getId(), u1);
        usuarioService.update(u2.getId(), u2);
        usuarioService.update(u3.getId(), u3);
        usuarioService.update(u4.getId(), u4);
    }

    @Transactional
    public void instantiateTestDatabase() {

    }

    public Set<Permissao> criarTodasPermissoes() {
        return permissaoService.saveIfNotExistent(Stream.of(PermissaoEnum.values())
                .map(Permissao::new).collect(Collectors.toSet()));
    }

    public Set<Permissao> getPermissoesOrganizador(Set<Permissao> todasPermissoes) {
        Set<String> permissoes = Stream.of(
                PermissaoEnum.CONSULTAR_EVENTO.name(), PermissaoEnum.CONSULTAR_MINISTRANTE.name(),
                PermissaoEnum.CRIAR_EVENTO.name(), PermissaoEnum.CRIAR_MINISTRANTE.name(),
                PermissaoEnum.EDITAR_EVENTO.name(), PermissaoEnum.EDITAR_MINISTRANTE.name(),
                PermissaoEnum.DELETAR_EVENTO.name(), PermissaoEnum.DELETAR_MINISTRANTE.name(),
                PermissaoEnum.INSCREVER_SE.name()
        ).collect(Collectors.toSet());
        return todasPermissoes.stream().filter(permissao -> permissoes.contains(permissao.getKey())).collect(Collectors.toSet());
    }

    public Set<Permissao> getPermissoesParticipante(Set<Permissao> todasPermissoes) {
        Set<String> permissoes = Stream.of(
                PermissaoEnum.INSCREVER_SE.name()
        ).collect(Collectors.toSet());
        return todasPermissoes.stream().filter(permissao -> permissoes.contains(permissao.getKey())).collect(Collectors.toSet());
    }
}
