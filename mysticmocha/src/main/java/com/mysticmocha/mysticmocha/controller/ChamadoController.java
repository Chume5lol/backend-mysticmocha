package com.mysticmocha.mysticmocha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.service.ChamadoService;

@RestController
@RequestMapping(value = { "/chamados" })
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

     @PostMapping("/criarChamado")
    public ResponseEntity<?> criarChamado(@RequestBody Chamado chamado) {
        try {
            chamadoService.criarChamado(chamado);
            return ResponseEntity.ok("Chamado criado com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    

}
