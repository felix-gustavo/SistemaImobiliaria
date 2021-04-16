package com.felix_lidia.lab04.imobiliaria.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.felix_lidia.lab04.imobiliaria.model.Cliente;
import com.felix_lidia.lab04.imobiliaria.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/pagination")
    public Page<Cliente> lista(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao) {
        return clienteService.buscaCom(paginacao);
    }

    @GetMapping
    @Cacheable(value = "listaDeClientes")
    public List<Cliente> todos() {
        return clienteService.todos();
    }

    @GetMapping("/{id}")
    public Cliente buscaPor(@PathVariable Integer id) {
        return clienteService.buscaPor(id);
    }

    @PostMapping
    @CacheEvict( value = "listaDeClientes", allEntries = true)
    public ResponseEntity<Cliente> cria(@Validated @RequestBody Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = clienteService.salva(cliente);
        return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict( value = "listaDeClientes", allEntries = true)
    public ResponseEntity<Cliente> atualiza(@PathVariable Integer id, @Validated @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualiza(id, cliente);

        return ResponseEntity.ok(clienteAtualizado);
    }
}