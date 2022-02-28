package com.jcabrera.whatsappdemo.controller;

import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.model.Webhook;
import com.jcabrera.whatsappdemo.service.WebhookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/webhook")
@Tag(name = "Webhook")
public class WebhookController {
  
  @Autowired
  WebhookService service;

  @PostMapping("/receive")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Receives data from ChatAPI", description = "Gets the message and stores it in the database")
  public Message receive(@RequestBody Webhook request) {
    System.out.println("receive(request): " + request);
    return service.receiveFromChatAPI(request);
  }
  
  @PostMapping("/send")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Sends data to ChatAPI", description = "Stores a message in the database and sends it to ChatAPI.")
  public Message send(@RequestBody Message message){
    return service.sendToChatAPI(message);
  }
}
