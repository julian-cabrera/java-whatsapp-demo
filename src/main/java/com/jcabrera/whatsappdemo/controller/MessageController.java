package com.jcabrera.whatsappdemo.controller;

import java.util.List;

import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
  @Autowired
  private MessageService service;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Message getMessage(@PathVariable String id) {
    return service.get(Long.parseLong(id));
  }
  
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public List<Message> getAllMessages() {
    return service.getAll();
  }

  
}
