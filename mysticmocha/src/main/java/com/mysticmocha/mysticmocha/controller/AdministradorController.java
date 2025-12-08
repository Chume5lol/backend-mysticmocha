package com.mysticmocha.mysticmocha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.dto.UsuarioDTO;
import com.mysticmocha.mysticmocha.service.ChamadoService;
import com.mysticmocha.mysticmocha.service.UsuarioService;

@RestController
@RequestMapping(value = { "/administrador" })
@CrossOrigin(origins = "http://localhost:5174")
public class AdministradorController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/chamados")
    public ResponseEntity<List<Chamado>> chamados() {
        return ResponseEntity.ok(chamadoService.listaChamadosGerais());
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> usuarios() {
        List<UsuarioDTO> dtos = usuarioService.buscarUsuarios()
                .stream()
                .map(u -> new UsuarioDTO(
                        u.getId(),
                        u.getNome(),
                        u.getSobrenome(),
                        u.getEmail(),
                        u.getNickname(),
                        u.getDepartamento(),
                        u.getCargo(),
                        u.getPerfil(),
                        u.getHabilitado()))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Usuario usuario) {
        try {
            usuarioService.createUser(usuario);
            return ResponseEntity.ok("Novo cliente cadastrado com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuario));
    }

}
