package br.uesc.eventos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uesc.eventos.entity.Ministrante;
import br.uesc.eventos.repository.MinistranteRepository;
import br.uesc.eventos.service.MinistranteService;

@RestController
@RequestMapping("/ministrantes")
public class MinistranteController extends BaseController<Ministrante, MinistranteRepository, MinistranteService> {

}
