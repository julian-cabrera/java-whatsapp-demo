package com.jcabrera.whatsappdemo.repository;

import com.jcabrera.whatsappdemo.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


  //Correction: jpa doesn't support MAX 
  @Query("select max(m.time) from Message m where m.chat_id like %?1%")
  public Message getLastMessage(String chatID, int time);
}
