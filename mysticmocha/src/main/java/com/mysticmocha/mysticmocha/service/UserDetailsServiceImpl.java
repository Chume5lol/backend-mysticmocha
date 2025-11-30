package com.mysticmocha.mysticmocha.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.repository.UsuarioRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository; // seu repo JPA

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
    Usuario u = usuarioRepository.findByNickname(nickname)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    List<SimpleGrantedAuthority> authorities = List.of(
        new SimpleGrantedAuthority("ROLE_" + u.getPerfil().name())
    );

    return new org.springframework.security.core.userdetails.User(
        u.getNickname(), 
        u.getSenha(),
        authorities
    );
}

}
