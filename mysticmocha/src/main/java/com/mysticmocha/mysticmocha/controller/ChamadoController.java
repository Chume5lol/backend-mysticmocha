package com.mysticmocha.mysticmocha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.domain.Mensagem;
import com.mysticmocha.mysticmocha.service.ChamadoService;
import com.mysticmocha.mysticmocha.service.EmailServiceImpl;

@RestController
@RequestMapping(value = { "/chamados" })
@CrossOrigin(origins = "http://localhost:5174")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @PostMapping("/criarChamado")
    public ResponseEntity<?> criarChamado(@RequestBody Chamado chamado) {
        try {
            chamado.setStatus("Aguardando atendimento");
            chamadoService.criarChamado(chamado);

            try {
                emailServiceImpl.sendSimpleMessage(
                        "chumelol2015@gmail.com",
                        "Chamado criado",
                        "Seu chamado foi criado com sucesso!");
            } catch (Exception ignored) {
                
            }

            return ResponseEntity.ok("Chamado criado com sucesso!");

        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Chamado>> chamados(@PathVariable Long id) {
        return ResponseEntity.ok(chamadoService.chamadosUsuario(id));
    }

    @GetMapping("/{id}/mensagens")
    public ResponseEntity<List<Mensagem>> listarMensagens(@PathVariable Long id) {

        List<Mensagem> mensagens = chamadoService.buscarMensagensPorChamado(id);

        return ResponseEntity.ok(mensagens);
    }

}
