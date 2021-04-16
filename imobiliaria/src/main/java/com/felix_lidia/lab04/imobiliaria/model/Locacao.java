package com.felix_lidia.lab04.imobiliaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    @NotNull(message = "Imóvel obrigatório")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "cliente obrigatório")
    private Cliente cliente;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Valor de aluguel obrigatório")
    private BigDecimal valorAluguel;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Percentual de multa obrigatório")
    private BigDecimal percentualMulta;

    @Column
    @NotNull(message = "Dia de vencimento obrigatório")
    private Integer diaVencimento;

    @Column
    @NotNull(message = "Data de inicio obrigatório")
    @PastOrPresent
    private LocalDate dataInicio;

    @Column
    @NotNull(message = "Data fim obrigatório")
    @Future
    private LocalDate dataFim;

    @Column
    @NotNull(message = "ativo obrigatório")
    private Boolean ativo;

    @Column(columnDefinition = "TEXT")
    private String obs;

    public Locacao(Imovel imovel, Cliente cliente, BigDecimal valorAluguel, BigDecimal percentualMulta,
            Integer diaVencimento, LocalDate dataInicio, LocalDate dataFim, Boolean ativo, String obs) {
        this.imovel = imovel;
        this.cliente = cliente;
        this.valorAluguel = valorAluguel;
        this.percentualMulta = percentualMulta;
        this.diaVencimento = diaVencimento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = ativo;
        this.obs = obs;
    }
}