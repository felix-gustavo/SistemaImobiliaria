package com.felix_lidia.lab04.imobiliaria.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.felix_lidia.lab04.imobiliaria.controller.requestBody.LocacaoReq;
import com.felix_lidia.lab04.imobiliaria.model.Cliente;
import com.felix_lidia.lab04.imobiliaria.model.Imovel;
import com.felix_lidia.lab04.imobiliaria.model.Locacao;
import com.felix_lidia.lab04.imobiliaria.service.ClienteService;
import com.felix_lidia.lab04.imobiliaria.service.ImovelService;
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

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;
    private final ClienteService clienteService;
    private final ImovelService imovelService;

    @Autowired
    public LocacaoController(LocacaoService locacaoService, ClienteService clienteService,
            ImovelService imovelService) {
        this.locacaoService = locacaoService;
        this.clienteService = clienteService;
        this.imovelService = imovelService;
    }

    @GetMapping("/pagination")
    public Page<Locacao> lista(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        return locacaoService.buscaCom(paginacao);
    }

    @GetMapping
    public List<Locacao> todos() {
        return locacaoService.todos();
    }

    @GetMapping("/{id}")
    public Locacao buscaPor(@PathVariable Integer id) {
        return locacaoService.buscaPor(id);
    }

    @PostMapping
    public ResponseEntity<Locacao> cria(@Validated @RequestBody LocacaoReq locacaoReq, HttpServletResponse response) {
        Cliente cliente = clienteService.buscaPor(locacaoReq.getCliente());
        Imovel imovel = imovelService.buscaPor(locacaoReq.getImovel());

        Locacao locacao = new Locacao(imovel, cliente, locacaoReq.getValorAluguel(), locacaoReq.getPercentualMulta(),
                locacaoReq.getDiaVencimento(), locacaoReq.getDataInicio(), locacaoReq.getDataFim(),
                locacaoReq.getAtivo(), locacaoReq.getObs());

        Locacao locacaoSalva = locacaoService.salva(locacao);
        return new ResponseEntity<Locacao>(locacaoSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locacao> atualiza(@Validated @RequestBody LocacaoReq locacaoReq, @PathVariable Integer id) {
        Cliente cliente = clienteService.buscaPor(locacaoReq.getCliente());
        Imovel imovel = imovelService.buscaPor(locacaoReq.getImovel());

        Locacao locacao = new Locacao(imovel, cliente, locacaoReq.getValorAluguel(), locacaoReq.getPercentualMulta(),
                locacaoReq.getDiaVencimento(), locacaoReq.getDataInicio(), locacaoReq.getDataFim(),
                locacaoReq.getAtivo(), locacaoReq.getObs());

        Locacao locacaoAtualizado = locacaoService.atualiza(id, locacao);

        return ResponseEntity.ok(locacaoAtualizado);
    }
}