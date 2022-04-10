package com.jcabrera.whatsappdemo.service.impl;

import java.util.List;

import com.jcabrera.whatsappdemo.exception.ResourceNotFoundException;
import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.repository.MessageRepository;
import com.jcabrera.whatsappdemo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImp implements MessageService {

  @Autowired
  private MessageRepository repo;
  
  @Override
  public Message get(Long id) {
    return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message with ID " + id + " could not be found"));
  }

  @Override
  public List<Message> getByChatID(String chatID) {
    return repo.getByChatID(chatID);
  }

  @Override
  public Message getLastMessage(String chatID) {
    return repo.getLastMessage(chatID);
  }

  @Override
  public List<Message> getAll() {
    return repo.findAll();
  }

  @Override
  public Message save(Message message) {
    return repo.save(message);
  }
  
  @Override
  public Message update(Long id, Message message) {
    Message actualMessage = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message with ID" + id + "could not be found. Could not update message."));
    actualMessage.update(message);
    return actualMessage;
  }

  @Override
  public Message delete(Long id) {
    Message message = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message with ID" + id + "could not be found. Could not delete message."));
    return message;
  }
}
