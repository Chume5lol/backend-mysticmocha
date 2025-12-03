package com.mysticmocha.mysticmocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mysticmocha.mysticmocha.domain.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
