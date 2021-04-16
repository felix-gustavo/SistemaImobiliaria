package com.felix_lidia.lab04.imobiliaria.service;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Cliente;
import com.felix_lidia.lab04.imobiliaria.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final GenericoService<Cliente> genericoService;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.genericoService = new GenericoService<Cliente>(clienteRepository);
    }

    @Transactional(readOnly = true)
    public Cliente buscaPor(Integer id) {
        return this.genericoService.buscaPor(id);
    }

    public Page<Cliente> buscaPor(Integer id, Pageable paginacao) {
        return clienteRepository.findByIdContaining(id, paginacao);
    }

    public Page<Cliente> buscaCom(Pageable paginacao) {
        return clienteRepository.findAll(paginacao);
    }

    @Transactional
    public Cliente salva(Cliente cliente) {
        return this.genericoService.salva(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> todos() {
        return genericoService.todos();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id);
    }

    @Transactional
    public Cliente atualiza(Integer id, Cliente cliente) {
        return this.genericoService.atualiza(cliente, id);
    }
}