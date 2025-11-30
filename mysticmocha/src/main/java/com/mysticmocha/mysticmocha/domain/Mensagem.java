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
public class Mensagem {

    private Long id;

    private Long idChamado;

    private Long idChat;

    private Usuario remetente;

    private String descricao;

    private LocalDateTime dataEnvio;
}
