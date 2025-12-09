package com.mysticmocha.mysticmocha.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.domain.Chat;
import com.mysticmocha.mysticmocha.domain.Mensagem;
import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.repository.ChamadoRepository;
import com.mysticmocha.mysticmocha.repository.UsuarioRepository;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    EmailServiceImpl emailServiceImpl;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Chamado criarChamado(Chamado chamado) {
        Chat chat = new Chat();
        chat.setChamado(chamado);
        chat.setSolicitante(chamado.getSolicitante());
        chat.setAtendente(chamado.getAtendente());
        chat.setMensagens(new ArrayList<>());

        chamado.setChat(chat);

        chamadoRepository.save(chamado);

        Usuario solicitante = usuarioRepository.findById(chamado.getSolicitante().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String solicitanteEmail = solicitante.getEmail();

        System.out.println(solicitanteEmail);

        return chamado;
    }

    public void atualizarChamado(Long idChamado, String status, LocalDateTime dataAtualizacao) {
        Chamado chamado = chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setStatus(status);
        chamado.setChat(new Chat());
        chamado.setDataAtualizacao(dataAtualizacao);

        chamadoRepository.save(chamado);

    }

    public List<Chamado> chamadosUsuario(Long id) {

        List<Chamado> chamados = chamadoRepository.findAllBySolicitanteId(id);

        return chamados;

    }

    public List<Chamado> listaChamadosGerais() {

        List<Chamado> chamados = chamadoRepository.findAll();

        return chamados;
    }

    public List<Mensagem> buscarMensagensPorChamado(Long id) {

        List<Mensagem> mensagens = chamadoRepository.findAllMensagensByChatId(id);

        return mensagens;
    }

}
