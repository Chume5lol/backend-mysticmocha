package com.mysticmocha.mysticmocha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysticmocha.mysticmocha.domain.Administrador;
import com.mysticmocha.mysticmocha.domain.Cliente;
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
            default:
                return createAndSaveCliente(usuario);
        }
    }

    private Administrador createAndSaveAdministrador(Usuario u) {
        Administrador admin = new Administrador(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(),
                u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        return usuarioRepository.save(admin);
    }

    private Prestador createAndSavePrestador(Usuario u) {
        Prestador prestador = new Prestador(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(),
                u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        return usuarioRepository.save(prestador);
    }

    private Cliente createAndSaveCliente(Usuario u) {
        Cliente cliente = new Cliente(u.getId(), u.getNome(), u.getSobrenome(), u.getEmail(), u.getNickname(),
                u.getDepartamento(), u.getCargo(), u.getSenha(), u.getPerfil(), u.getHabilitado());
        return usuarioRepository.save(cliente);
    }


    public List<Usuario> buscarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios;
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuarioAtualizado.getNome() != null)
            usuario.setNome(usuarioAtualizado.getNome());
        if (usuarioAtualizado.getSobrenome() != null)
            usuario.setSobrenome(usuarioAtualizado.getSobrenome());
        if (usuarioAtualizado.getEmail() != null)
            usuario.setEmail(usuarioAtualizado.getEmail());
        if (usuarioAtualizado.getNickname() != null)
            usuario.setNickname(usuarioAtualizado.getNickname());
        if (usuarioAtualizado.getCargo() != null)
            usuario.setCargo(usuarioAtualizado.getCargo());
        if (usuarioAtualizado.getPerfil() != null)
            usuario.setPerfil(usuarioAtualizado.getPerfil());
        if (usuarioAtualizado.getSenha() != null) {
            usuario.setSenha(encoder.encode(usuarioAtualizado.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

}
