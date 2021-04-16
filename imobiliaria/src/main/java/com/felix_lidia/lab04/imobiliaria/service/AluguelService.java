package com.felix_lidia.lab04.imobiliaria.service;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Aluguel;
import com.felix_lidia.lab04.imobiliaria.repository.AluguelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AluguelService {
    private final GenericoService<Aluguel> genericoService;
    private final AluguelRepository aluguelRepository;

    @Autowired
    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
        this.genericoService = new GenericoService<Aluguel>(aluguelRepository);
    }

    @Transactional(readOnly = true)
    public Aluguel buscaPor(Integer id) {
        return this.genericoService.buscaPor(id);
    }

    public Page<Aluguel> buscaPor(Integer id, Pageable paginacao) {
        return aluguelRepository.findByIdContaining(id, paginacao);
    }

    public Page<Aluguel> buscaCom(Pageable paginacao) {
        return aluguelRepository.findAll(paginacao);
    }

    @Transactional
    public Aluguel salva(Aluguel aluguel) {
        return this.genericoService.salva(aluguel);
    }

    @Transactional(readOnly = true)
    public List<Aluguel> todos() {
        return genericoService.todos();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id);
    }

    @Transactional
    public Aluguel atualiza(Integer id, Aluguel aluguel) {
        return this.genericoService.atualiza(aluguel, id);
    }
}