package com.felix_lidia.lab04.imobiliaria.repository;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Locacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {
    List<Locacao> findByIdContaining(Integer id);
    Page<Locacao> findByIdContaining(Integer id, Pageable paginacao);
}