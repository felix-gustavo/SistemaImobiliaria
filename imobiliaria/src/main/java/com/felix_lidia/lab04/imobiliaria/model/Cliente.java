package com.felix_lidia.lab04.imobiliaria.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome obrigatório")
    private String nome_cliente;

    @NotBlank(message = "CPF obrigatório")
    @CPF(message = "CPF inválido")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "Telefone obrigatório")
    @Column(length = 12)
    private String telefone;

    @NotBlank(message = "Email obrigatório")
    @Column(length = 100)
    private String email;

    @NotNull(message = "Data de nascimento obrigatório")
    @Past
    private LocalDate dt_nascimento;

    public Cliente(String nome_cliente, String cpf, String telefone, String email, LocalDate dt_nascimento) {
        this.nome_cliente = nome_cliente;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dt_nascimento = dt_nascimento;
    }
}