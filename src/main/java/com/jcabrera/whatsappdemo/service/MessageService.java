package com.jcabrera.whatsappdemo.service;

import java.util.List;

import com.jcabrera.whatsappdemo.model.Message;

public interface MessageService {
  public Message get(Long id);

  public Message getByChatId(String chatId);

  public Message getLastMessage(String chatID, int time);

  public List<Message> getAll();

  public Message save(Message message);

  public Message update(Long id, Message message);

  public Message delete(Long id);
}
