package com.mysticmocha.mysticmocha.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import com.mysticmocha.mysticmocha.domain.LoginRequest;
import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.repository.UsuarioRepository;
import com.mysticmocha.mysticmocha.service.AuthService;
import com.mysticmocha.mysticmocha.service.UsuarioService;
import com.mysticmocha.mysticmocha.domain.Gestor;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Gestor usuario) {
        try {
            usuarioService.createUser(usuario);
            return ResponseEntity.ok("Novo cliente cadastrado com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // faz login e gera token
            String token = authService.login(loginRequest.getNickname(), loginRequest.getSenha());

            // busca o usuário logado
            Optional<Usuario> usuario = usuarioRepository.findByNickname(loginRequest.getNickname());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "usuario", usuario,
                    "message", "Login realizado com sucesso!"));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Nickname ou senha inválidos!"));
        }
    }

}
