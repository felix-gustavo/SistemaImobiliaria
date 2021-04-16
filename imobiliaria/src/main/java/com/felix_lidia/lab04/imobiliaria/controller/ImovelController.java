package com.felix_lidia.lab04.imobiliaria.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.felix_lidia.lab04.imobiliaria.model.Imovel;
import com.felix_lidia.lab04.imobiliaria.service.ImovelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    private final ImovelService imovelService;

    @Autowired
    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @GetMapping("/pagination")
    public Page<Imovel> lista(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        return imovelService.buscaCom(paginacao);
    }

    @GetMapping
    public List<Imovel> todos() {
        return imovelService.todos();
    }

    @GetMapping("/{id}")
    public Imovel buscaPor(@PathVariable Integer id) {
        return imovelService.buscaPor(id);
    }

    @PostMapping
    public ResponseEntity<Imovel> cria(@Validated @RequestBody Imovel imovel, HttpServletResponse response) {
        Imovel imovelSalvo = imovelService.salva(imovel);
        return new ResponseEntity<Imovel>(imovelSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualiza(@PathVariable Integer id, @Validated @RequestBody Imovel imovel) {
        Imovel imovelAtualizado = imovelService.atualiza(id, imovel);

        return ResponseEntity.ok(imovelAtualizado);
    }
}