package com.mysticmocha.mysticmocha.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idChamado;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "remetente_id", nullable = false)
    private Usuario remetente;

    @Column
    private String descricao;

    @Column
    private LocalDateTime dataEnvio;

    public Mensagem() {
    }

    public Mensagem(Long id, Long idChamado, Chat chat, Usuario remetente, String descricao, LocalDateTime dataEnvio) {
        this.id = id;
        this.idChamado = idChamado;
        this.chat = chat;
        this.remetente = remetente;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
    }

    
    
}
