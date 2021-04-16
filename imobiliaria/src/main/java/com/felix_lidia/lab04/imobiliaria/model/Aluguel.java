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
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull(message = "Locação obrigatória")
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @FutureOrPresent
    @NotNull(message = "Data de vencimento obrigatória")
    private LocalDate dt_vencimento;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Valor pago obrigatório")
    private BigDecimal valor_pago;

    @Column(columnDefinition = "TEXT")
    private String obs;

    public Aluguel(Locacao locacao, LocalDate dt_vencimento, BigDecimal valor_pago, String obs) {
        this.locacao = locacao;
        this.dt_vencimento = dt_vencimento;
        this.valor_pago = valor_pago;
        this.obs = obs;
    }
}