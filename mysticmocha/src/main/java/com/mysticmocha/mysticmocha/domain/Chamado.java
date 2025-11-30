package com.mysticmocha.mysticmocha.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Chamado {

    private Long id;

    private String categoria;

    private String titulo;

    private String descricao;

    private Usuario solicitante;

    private Usuario atendente;

    private Chat chat;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataAtualizacao;

    private LocalDateTime dataFechamento;

    private String status;
}
