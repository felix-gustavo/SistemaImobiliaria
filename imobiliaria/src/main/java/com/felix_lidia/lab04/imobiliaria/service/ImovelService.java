package com.felix_lidia.lab04.imobiliaria.service;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Imovel;
import com.felix_lidia.lab04.imobiliaria.repository.ImovelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImovelService {
    private final GenericoService<Imovel> genericoService;
    private final ImovelRepository imovelRepository;

    @Autowired
    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
        this.genericoService = new GenericoService<Imovel>(imovelRepository);
    }

    @Transactional(readOnly = true)
    public Imovel buscaPor(Integer id) {
        return this.genericoService.buscaPor(id);
    }

    public Page<Imovel> buscaPor(Integer id, Pageable paginacao) {
        return imovelRepository.findByIdContaining(id, paginacao);
    }

    public Page<Imovel> buscaCom(Pageable paginacao) {
        return imovelRepository.findAll(paginacao);
    }

    @Transactional
    public Imovel salva(Imovel imovel) {
        return this.genericoService.salva(imovel);
    }

    @Transactional(readOnly = true)
    public List<Imovel> todos() {
        return genericoService.todos();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluirPor(id);
    }

    @Transactional
    public Imovel atualiza(Integer id, Imovel imovel) {
        return this.genericoService.atualiza(imovel, id);
    }
}