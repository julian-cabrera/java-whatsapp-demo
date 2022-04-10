package com.jcabrera.whatsappdemo.repository;

import java.util.List;

import com.jcabrera.whatsappdemo.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query(value = "SELECT TOP 1(m.body) FROM t_wsp_message m WHERE m.chat_id LIKE %?1% ORDER BY m.time DESC", nativeQuery = true)
  public Message getLastMessage(String chatID);

  @Query("SELECT m FROM Message m WHERE m.chatId LIKE %?1% ORDER BY m.messageNumber")
  public List<Message> getByChatID(String chatID);

}
