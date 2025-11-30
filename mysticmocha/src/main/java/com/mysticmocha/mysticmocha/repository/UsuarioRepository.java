package com.mysticmocha.mysticmocha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysticmocha.mysticmocha.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNickname(String nickname);

}
