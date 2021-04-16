package com.felix_lidia.lab04.imobiliaria.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 12)
    @NotNull(message = "Tipo de imóvel obrigatório")
    private String tipo_imovel;

    @NotNull(message = "Endereço obrigatório")
    private String endereco;

    @NotNull(message = "Cep obrigatório")
    @Column(length = 10)
    private String cep;

    @NotNull(message = "Dormitorios obrigatório")
    private Integer dormitorios;

    @NotNull(message = "Banheiros obrigatório")
    private Integer banheiros;

    @NotNull(message = "Suítes obrigatório")
    private Integer suites;

    @NotNull(message = "Metragem obrigatório")
    private Integer metragem;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Valor do aluguel sugerido obrigatório")
    private BigDecimal valor_aluguel_sug;

    @Column(columnDefinition = "TEXT")
    private String obs;

    public Imovel(Integer id, String tipo_imovel, String endereco, String cep, Integer dormitorios, Integer banheiros,
            Integer suites, Integer metragem, BigDecimal valor_aluguel_sug, String obs) {
        this.id = id;
        this.tipo_imovel = tipo_imovel;
        this.endereco = endereco;
        this.cep = cep;
        this.dormitorios = dormitorios;
        this.banheiros = banheiros;
        this.suites = suites;
        this.metragem = metragem;
        this.valor_aluguel_sug = valor_aluguel_sug;
        this.obs = obs;
    }
}