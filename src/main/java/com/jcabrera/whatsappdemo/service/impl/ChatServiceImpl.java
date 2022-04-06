package com.jcabrera.whatsappdemo.service.impl;

import java.util.List;

import com.jcabrera.whatsappdemo.exception.ResourceNotFoundException;
import com.jcabrera.whatsappdemo.model.Chat;
import com.jcabrera.whatsappdemo.repository.ChatRepository;
import com.jcabrera.whatsappdemo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
  
  @Autowired
  ChatRepository repo;
  
  // @Override
  // public Chat get(Long id) {
  //   return repo.findById(id)
  //       .orElseThrow(() -> new ResourceNotFoundException(String.format("Chat with ID %s could not be found.", id)));
  // }
  
  @Override
  public Chat getByChatId(String chatId) {
    return repo.findByChatId(chatId);
  }

  @Override
  public List<?> getChatsWithLastMessage(){
    return repo.getChatsWithLastMessage();
  }
  
  @Override
  public List<Chat> getAll() {
    return repo.findAll();
  }

  @Override
  public Chat save(Chat chat) {
    return repo.save(chat);
  }

  @Override
  public Chat update(Long id, Chat chat) {
    Chat actualChat = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Chat with ID %s could not be found. Could not update Chat", id)));
    actualChat.update(chat);
    return actualChat;
  }

  @Override
  public Chat delete(Long id) {
    Chat chat = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Chat with ID %s could not be found. Could not delete Chat", id)));
    return chat;
  }
}
