package com.mysticmocha.mysticmocha.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String codigoProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, String codigoProduto, Integer quantidade, Double preco,
            Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.ativo = ativo;
    }

    
}
