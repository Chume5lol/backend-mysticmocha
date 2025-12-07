package com.mysticmocha.mysticmocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysticmocha.mysticmocha.domain.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}
