package com.felix_lidia.lab04.imobiliaria.service;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Locacao;
import com.felix_lidia.lab04.imobiliaria.repository.LocacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocacaoService {
    private final GenericoService<Locacao> genericoService;
    private final LocacaoRepository locacaoRepository;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
        this.genericoService = new GenericoService<Locacao>(locacaoRepository);
    }

    @Transactional(readOnly = true)
    public Locacao buscaPor(Integer id) {
        return this.genericoService.buscaPor(id);
    }

    public Page<Locacao> buscaPor(Integer id, Pageable paginacao) {
        return locacaoRepository.findByIdContaining(id, paginacao);
    }

    public Page<Locacao> buscaCom(Pageable paginacao) {
        return locacaoRepository.findAll(paginacao);
    }

    @Transactional
    public Locacao salva(Locacao locacao) {
        return this.genericoService.salva(locacao);
    }

    @Transactional(readOnly = true)
    public List<Locacao> todos() {
        return genericoService.todos();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id);
    }

    @Transactional
    public Locacao atualiza(Integer id, Locacao locacao) {
        return this.genericoService.atualiza(locacao, id);
    }
}