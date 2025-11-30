package com.mysticmocha.mysticmocha.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mysticmocha.mysticmocha.domain.ENUMS.Perfil;
import com.mysticmocha.mysticmocha.domain.ENUMS.Setor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 150)
    private String sobrenome;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 70)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Setor departamento;

    @Column(nullable = false, length = 70)
    private String cargo;

    @Column(nullable = false)
    private String senha;

	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    @Column(nullable = false)
    private Boolean habilitado;

	public Usuario(Boolean habilitado) {
		this.habilitado = true;
	}

	public Usuario(Long id, String nome, String sobrenome, String email, String nickname, Setor departamento,
			String cargo, String senha, Perfil perfil, Boolean habilitado) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.nickname = nickname;
		this.departamento = departamento;
		this.cargo = cargo;
		this.senha = senha;
		this.perfil = perfil;
		this.habilitado = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
        String role = "ROLE_"+perfil.name();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nickname;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                perfil == usuario.perfil;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, nickname, senha, perfil);
	}
  

}
