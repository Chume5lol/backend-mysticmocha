package com.mysticmocha.mysticmocha.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mysticmocha.mysticmocha.dto.ChatMessageDTO;
import com.mysticmocha.mysticmocha.service.ChatService;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.send")
    @SendTo("/topic/chamado")
    public ChatMessageDTO enviarMensagem(ChatMessageDTO msg) {
        
        return chatService.enviarMensagem(msg, msg.getRemetenteId());
    }

    @PostMapping("/chamados/{id}/chat")
    public ResponseEntity<?> enviarMensagemHTTP(@PathVariable Long id, @RequestBody ChatMessageDTO msg) {
    try {
        ChatMessageDTO novaMsg = chatService.enviarMensagem(msg, id);
        return ResponseEntity.ok(novaMsg);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
