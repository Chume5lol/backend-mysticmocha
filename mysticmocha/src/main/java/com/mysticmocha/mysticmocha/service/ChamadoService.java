package com.mysticmocha.mysticmocha.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.repository.ChamadoRepository;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado criarChamado(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    public void atualizarChamado(Long idChamado, String status, LocalDateTime dataAtualizacao) {
        Chamado chamado = chamadoRepository.findById(idChamado)
            .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setStatus(status);
        chamado.setDataAtualizacao(dataAtualizacao);

        chamadoRepository.save(chamado);

    }
}
