package com.felix_lidia.lab04.imobiliaria.repository;

import java.util.List;

import com.felix_lidia.lab04.imobiliaria.model.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByIdContaining(Integer id);
    Page<Cliente> findByIdContaining(Integer id, Pageable paginacao);
}