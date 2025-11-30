package com.mysticmocha.mysticmocha.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Chat {

    private Long id;

    private Long chamadoId;

    private Usuario atendente;

    private Usuario solicitante;

    private List<Mensagem> mensagens;
}
