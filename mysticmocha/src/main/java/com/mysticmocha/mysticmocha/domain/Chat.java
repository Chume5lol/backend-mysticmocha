package com.mysticmocha.mysticmocha.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "chamado_id", nullable = false, unique = true)
    private Chamado chamado;

    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Usuario atendente;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensagem> mensagens;

    public Chat() {
    }

    public Chat(Long id, Chamado chamado, Usuario atendente, Usuario solicitante, List<Mensagem> mensagens) {
        this.id = id;
        this.chamado = chamado;
        this.atendente = atendente;
        this.solicitante = solicitante;
        this.mensagens = mensagens;
    }
    
}
