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

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5174")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // faz login e gera token
            String token = authService.login(loginRequest.getNickname(), loginRequest.getSenha());

            Optional<Usuario> usuario = usuarioRepository.findByNickname(loginRequest.getNickname());
            System.out.println("Usuário encontrado: " + usuario.isPresent());


            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Usuário não encontrado!"));
            }

            // monta o objeto do usuário para enviar ao frontend
            Map<String, Object> usuarioMap = Map.of(
                    "id", usuario.get().getId(),
                    "nome", usuario.get().getNome(),
                    "nickname", usuario.get().getNickname(),
                    "departamento", usuario.get().getDepartamento(),
                    "role", usuario.get().getPerfil() 
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
