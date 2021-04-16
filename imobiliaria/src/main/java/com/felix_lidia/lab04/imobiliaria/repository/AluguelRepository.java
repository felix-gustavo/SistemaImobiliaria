package com.felix_lidia.lab04.imobiliaria.repository;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Aluguel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {
    List<Aluguel> findByIdContaining(Integer id);
    Page<Aluguel> findByIdContaining(Integer id, Pageable paginacao);  
}