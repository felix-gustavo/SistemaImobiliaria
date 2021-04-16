package com.felix_lidia.lab04.imobiliaria.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.felix_lidia.lab04.imobiliaria.controller.requestBody.AluguelReq;
import com.felix_lidia.lab04.imobiliaria.model.Aluguel;
import com.felix_lidia.lab04.imobiliaria.model.Locacao;
import com.felix_lidia.lab04.imobiliaria.service.AluguelService;
import com.felix_lidia.lab04.imobiliaria.service.LocacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/alugueis")
@RestController
public class AluguelController {
    private final AluguelService aluguelService;
    private final LocacaoService locacaoService;

    @Autowired
    public AluguelController(AluguelService aluguelService, LocacaoService locacaoService) {
        this.aluguelService = aluguelService;
        this.locacaoService = locacaoService;
    }

    @GetMapping("/pagination")
    public Page<Aluguel> lista(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        return aluguelService.buscaCom(paginacao);
    }

    @GetMapping
    public List<Aluguel> todos() {
        return aluguelService.todos();
    }

    @GetMapping("/{id}")
    public Aluguel buscaPor(@PathVariable Integer id) {
        return aluguelService.buscaPor(id);
    }

    @PostMapping
    public ResponseEntity<Aluguel> cria(@Validated @RequestBody AluguelReq aluguelReq, HttpServletResponse response) {
        Locacao locacao = locacaoService.buscaPor(aluguelReq.getLocacao());
        Aluguel aluguel = new Aluguel(locacao, aluguelReq.getDt_vencimento(), aluguelReq.getValor_pago(),
                aluguelReq.getObs());

        Aluguel aluguelSalvo = aluguelService.salva(aluguel);
        return new ResponseEntity<Aluguel>(aluguelSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> atualiza(@PathVariable Integer id, @Validated @RequestBody AluguelReq aluguelReq) {
        Locacao locacao = locacaoService.buscaPor(aluguelReq.getLocacao());

        Aluguel aluguel = new Aluguel(locacao, aluguelReq.getDt_vencimento(), aluguelReq.getValor_pago(),
                aluguelReq.getObs());

        Aluguel aluguelAtualizado = aluguelService.atualiza(id, aluguel);

        return ResponseEntity.ok(aluguelAtualizado);
    }
}