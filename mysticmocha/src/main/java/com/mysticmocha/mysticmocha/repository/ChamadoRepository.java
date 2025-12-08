package com.mysticmocha.mysticmocha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mysticmocha.mysticmocha.domain.Chamado;
import com.mysticmocha.mysticmocha.domain.Mensagem;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query(value = "SELECT * FROM chamado WHERE solicitante_id = ?", nativeQuery = true)
    List<Chamado> findAllBySolicitanteId(Long id);

    @Query(value = "SELECT * FROM mensagem WHERE chat_id = ? ORDER BY data_envio ASC", nativeQuery = true)
    List<Mensagem> findAllMensagensByChatId(Long chatId);

}
