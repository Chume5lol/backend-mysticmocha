package com.mysticmocha.mysticmocha.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.domain.Chat;
import com.mysticmocha.mysticmocha.domain.Mensagem;
import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.dto.ChatMessageDTO;
import com.mysticmocha.mysticmocha.repository.ChatRepository;
import com.mysticmocha.mysticmocha.repository.MensagemRepository;
import com.mysticmocha.mysticmocha.repository.UsuarioRepository;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ChatService(ChatRepository chatRepository,
                       MensagemRepository mensagemRepository,
                       UsuarioRepository usuarioRepository) {
        this.chatRepository = chatRepository;
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ChatMessageDTO enviarMensagem(ChatMessageDTO dto, Long id) {
        System.out.println(id);
        Chat chat = chatRepository.findByChamadoId(id)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));
        
        Usuario remetente = usuarioRepository.findById(dto.getRemetenteId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        Mensagem mensagem = new Mensagem();
        mensagem.setChat(chat);
        mensagem.setRemetente(remetente);
        mensagem.setDescricao(dto.getDescricao());
        mensagem.setDataEnvio(LocalDateTime.now());
        
        mensagemRepository.save(mensagem);
        
        return new ChatMessageDTO(mensagem);
    }

    public List<Mensagem> mensagensDoChamado(Long idChamado) {
        Chat chat = chatRepository.findByChamadoId(idChamado)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));
        return chat.getMensagens();
    }

}
