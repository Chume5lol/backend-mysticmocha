package com.mysticmocha.mysticmocha.dto;

import java.time.LocalDateTime;

import com.mysticmocha.mysticmocha.domain.Mensagem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
    private Long idChamado;
    private Long remetenteId;
    private String remetenteNome;
    private String descricao;
    private LocalDateTime dataEnvio;

    public ChatMessageDTO() {}

    public ChatMessageDTO(Mensagem mensagem) {
        this.idChamado = mensagem.getChat().getChamado().getId();
        this.remetenteId = mensagem.getRemetente().getId();
        this.remetenteNome = mensagem.getRemetente().getNome();
        this.descricao = mensagem.getDescricao();
        this.dataEnvio = mensagem.getDataEnvio();
    }

    

}
