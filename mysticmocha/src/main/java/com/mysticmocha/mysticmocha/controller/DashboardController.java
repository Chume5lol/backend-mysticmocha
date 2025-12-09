package com.mysticmocha.mysticmocha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.service.ChamadoService;
import com.mysticmocha.mysticmocha.service.UsuarioService;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired 
    private ChamadoService chamadoService;


    @GetMapping(value = "/maioresCriadoresChamados")
    public ResponseEntity<List<Usuario>> maioresCriadoresChamados() {
        return ResponseEntity.ok(usuarioService.buscarUsuarios());
    }

    @GetMapping(value = "/quantidadeChamados")
    public ResponseEntity<List<Chamado>> chamadosTotais() {
        return ResponseEntity.ok(chamadoService.listaChamadosGerais());
    }
}
