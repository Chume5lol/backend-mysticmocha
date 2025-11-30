package com.mysticmocha.mysticmocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.config.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String nickname, String senha) {
        try {
            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(nickname, senha));

            // Pega os detalhes do usuário autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera o token JWT
            return jwtUtil.generateToken(userDetails);

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Nickname ou senha inválidos!");
        }
    }
}
