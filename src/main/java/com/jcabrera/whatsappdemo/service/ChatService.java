package com.jcabrera.whatsappdemo.service;

import java.util.List;

import com.jcabrera.whatsappdemo.model.Chat;

public interface ChatService {
  // public Chat get(Long id);

  public Chat getByChatId(String chatId);

  public List<?> getChatsWithLastMessage();

  public List<Chat> getAll();

  public Chat save(Chat chat);

  public Chat update(Long id, Chat chat);

  public Chat delete(Long id);
}
