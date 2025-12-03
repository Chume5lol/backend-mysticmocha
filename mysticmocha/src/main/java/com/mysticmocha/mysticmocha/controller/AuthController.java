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
    
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // faz login e gera token
            String token = authService.login(loginRequest.getNickname(), loginRequest.getSenha());

            Optional<Usuario> usuario = usuarioRepository.findByNickname(loginRequest.getNickname());

            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Usuário não encontrado!"));
            }

            // monta o objeto do usuário para enviar ao frontend
            Map<String, Object> usuarioMap = Map.of(
                    "id", usuario.get().getId(),
                    "nome", usuario.get().getNome(),
                    "nickname", usuario.get().getNickname(),
                    "role", usuario.get().getPerfil() // role
            );

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "usuario", usuarioMap,
                    "message", "Login realizado com sucesso!"
            ));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Nickname ou senha inválidos!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

}
