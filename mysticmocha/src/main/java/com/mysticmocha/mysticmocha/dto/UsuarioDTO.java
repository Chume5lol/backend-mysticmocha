package com.mysticmocha.mysticmocha.dto;

import com.mysticmocha.mysticmocha.domain.Usuario;
import com.mysticmocha.mysticmocha.domain.ENUMS.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String nickname;
    private String email;
    private String departamento;
    private String cargo;
    private Perfil perfil;
    private Boolean habilitado;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String sobrenome, String nickname, String email, String departamento,
            String cargo, Perfil perfil, Boolean habilitado) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nickname = nickname;
        this.email = email;
        this.departamento = departamento;
        this.cargo = cargo;
        this.perfil = perfil;
        this.habilitado = habilitado;
    }

    public UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(
                u.getId(),
                u.getNome(),
                u.getSobrenome(),
                u.getEmail(),
                u.getNickname(),
                u.getDepartamento(),
                u.getCargo(),
                u.getPerfil(),
                u.getHabilitado());
    }

}
