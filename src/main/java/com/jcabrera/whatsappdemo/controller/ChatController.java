package com.jcabrera.whatsappdemo.controller;

import java.util.List;

import com.jcabrera.whatsappdemo.model.Chat;
import com.jcabrera.whatsappdemo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/chat")
@Tag(name = "Chat")
public class ChatController {
  
  @Autowired
  ChatService service;
  
  @GetMapping("/full")
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public List<?> getChatsWithLastMessage(){
    return service.getChatsWithLastMessage();
  }

  @GetMapping
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public List<Chat>  getChats(){
    return service.getAll();
  }

}
