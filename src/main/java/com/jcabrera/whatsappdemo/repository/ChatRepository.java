package com.jcabrera.whatsappdemo.repository;

import com.jcabrera.whatsappdemo.model.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
  @Query("select c from Chat c where c.id like %?1%")
  public Chat findByChatId(String chatID);
}
