package com.felix_lidia.lab04.imobiliaria.repository;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Imovel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
    List<Imovel> findByIdContaining(Integer id);
    Page<Imovel> findByIdContaining(Integer id, Pageable paginacao);
}