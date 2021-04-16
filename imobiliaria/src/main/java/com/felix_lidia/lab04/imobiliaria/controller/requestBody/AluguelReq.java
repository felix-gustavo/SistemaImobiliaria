package com.felix_lidia.lab04.imobiliaria.controller.requestBody;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AluguelReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Locacao obrigatória")
    private Integer locacao;

    @FutureOrPresent
    @NotNull(message = "Data de vencimento obrigatória")
    private LocalDate dt_vencimento;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Valor pago obrigatório")
    private BigDecimal valor_pago;

    @Column(columnDefinition = "TEXT")
    private String obs;

    public AluguelReq(Integer locacao, LocalDate dt_vencimento, BigDecimal valor_pago, String obs) {
        this.locacao = locacao;
        this.dt_vencimento = dt_vencimento;
        this.valor_pago = valor_pago;
        this.obs = obs;
    }
}