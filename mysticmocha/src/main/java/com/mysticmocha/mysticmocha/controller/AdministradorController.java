package com.mysticmocha.mysticmocha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.service.ChamadoService;

@RestController
@RequestMapping(value = { "/administrador" })
@CrossOrigin(origins = "http://localhost:5174")
public class AdministradorController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/chamados")
    public ResponseEntity<List<Chamado>> chamados() {
        return ResponseEntity.ok(chamadoService.listaChamadosGerais());
    }

}
