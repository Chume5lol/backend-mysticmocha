package com.mysticmocha.mysticmocha.repository;

import com.mysticmocha.mysticmocha.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
