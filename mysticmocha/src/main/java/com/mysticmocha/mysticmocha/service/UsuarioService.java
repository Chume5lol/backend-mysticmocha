package com.mysticmocha.mysticmocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.domain.Administrador;
import com.mysticmocha.mysticmocha.domain.Cliente;
import com.mysticmocha.mysticmocha.domain.Gestor;
import com.mysticmocha.mysticmocha.domain.Prestador;
import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;  
    
      public Usuario createUser(Usuario usuario) {
        System.out.println(usuario);
        String passwordEncoded = encoder.encode(usuario.getSenha());
        usuario.setSenha(passwordEncoded);

        switch (usuario.getPerfil()) {
            case PRESTADOR:
                return createAndSavePrestador(usuario);
            case ADMINISTRADOR:
                return createAndSaveAdministrador(usuario);
            case CLIENTE:
                return createAndSaveCliente(usuario);
            case GESTOR:
                return createAndSaveGestor(usuario);
            default:
                return createAndSaveCliente(usuario);
        }
    }

    private Administrador createAndSaveAdministrador(Usuario u) {
        Administrador admin = new Administrador(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(), u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        return usuarioRepository.save(admin);
    }

    private Prestador createAndSavePrestador(Usuario u) {
        Prestador prestador = new Prestador(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(), u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        return usuarioRepository.save(prestador);
    }

    private Cliente createAndSaveCliente(Usuario u) {
        Cliente cliente = new Cliente(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(), u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        return usuarioRepository.save(cliente);
    }

    private Gestor createAndSaveGestor(Usuario u) {
        Gestor gestor = new Gestor(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(), u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        System.out.println(gestor);
        return usuarioRepository.save(gestor);
    }

}
