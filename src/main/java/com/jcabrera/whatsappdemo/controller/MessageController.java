package com.jcabrera.whatsappdemo.controller;

import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/message")
@Tag(name = "Message")
public class MessageController {
  
  @Autowired
  MessageService service;
  
  @GetMapping
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  public Message  getLastMessage(String chatID, int time){
    return service.getLastMessage(chatID, time);
  }
}