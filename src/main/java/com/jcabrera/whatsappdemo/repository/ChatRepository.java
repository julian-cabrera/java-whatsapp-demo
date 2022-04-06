package com.jcabrera.whatsappdemo.repository;

import java.util.List;

import com.jcabrera.whatsappdemo.model.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
  @Query("select c from Chat c where c.id like %?1%")
  public Chat findByChatId(String chatID);

  @Query(value = "SELECT c.*, (SELECT TOP 1 body FROM t_wsp_message m WHERE c.id = m.chat_id ORDER BY m.time) as last_message FROM t_wsp_chat c", nativeQuery = true)
  public List<?> getChatsWithLastMessage();
}
