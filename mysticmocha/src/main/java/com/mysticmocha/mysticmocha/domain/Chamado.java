package com.mysticmocha.mysticmocha.domain;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoria;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Usuario atendente;

    @OneToOne(mappedBy = "chamado", cascade = CascadeType.ALL)
    private Chat chat;

    @Column
    private LocalDateTime dataAbertura;

    @Column
    private LocalDateTime dataAtualizacao;

    @Column
    private LocalDateTime dataFechamento;

    @Column
    private String status;

    public Chamado() {
    }

    public Chamado(Long id, String categoria, String titulo, String descricao, Usuario solicitante, Usuario atendente,
            Chat chat, LocalDateTime dataAbertura, LocalDateTime dataAtualizacao, LocalDateTime dataFechamento,
            String status) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.solicitante = solicitante;
        this.atendente = atendente;
        this.chat = chat;
        this.dataAbertura = dataAbertura;
        this.dataAtualizacao = dataAtualizacao;
        this.dataFechamento = dataFechamento;
        this.status = status;
    }

    
}
