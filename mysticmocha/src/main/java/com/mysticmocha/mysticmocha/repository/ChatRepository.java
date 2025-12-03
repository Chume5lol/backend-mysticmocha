package com.mysticmocha.mysticmocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysticmocha.mysticmocha.domain.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>  {

}
