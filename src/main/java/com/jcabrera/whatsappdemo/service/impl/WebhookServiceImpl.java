package com.jcabrera.whatsappdemo.service.impl;


import com.jcabrera.whatsappdemo.exception.CouldNotCreateResourceException;
import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.model.Webhook;
import com.jcabrera.whatsappdemo.service.MessageService;
import com.jcabrera.whatsappdemo.service.WebhookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class WebhookServiceImpl implements WebhookService {
  
  @Autowired
  MessageService service;

  @Override
  public Message receiveFromChatAPI(Webhook request) throws CouldNotCreateResourceException {
    try {
      if (request.getMessages() != null && !request.getMessages().isEmpty()) {
        System.out.println("receiveFromChatAPI(Request:" + request + " )");
        return service.save(request.getMessages().get(0));
      }
    } catch (CouldNotCreateResourceException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Message sendToChatAPI(Message message) throws CouldNotCreateResourceException {
      RestTemplate restTemplate = new RestTemplate();
      String url = "";
      String messageType = "";

      if (message.getBody() != null && message.getBody().length() > 10000) {
        throw new CouldNotCreateResourceException("Message is too long, can't excede 10k characters.");
      }

      if (message.getAudio() != null && message.getAudio() != "") {
        // String idAudio = fileService.uploadFromUrl(message.getAudio(), "ogg");
        // message.setAudio("https://drive.google.com/uc?export=view&id=" + idAudio);
        // messageType = "/sendPTT?";
      }

      if (message.getType() == "chat" || message.getType() == null) {
        messageType = "/sendMessage?";
      } else {
        messageType = "/sendFile?";
        //fileService.uploadFromUrl(message.getBody(), )
      }

      //url = ApiClient.basePath + ApiClient.instanceId + messageType + ApiClient.token;
      url = "https://api.chat-api.com/{instance}" + messageType + "token={token}";
      
      HttpHeaders headers = new HttpHeaders();
      headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
      HttpEntity<?> entity = new HttpEntity<>(message, headers);

      ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.POST, entity, Message.class);

      System.out.println("sendToChatAPI(Request:" + message + " )");
      return service.save(message);
  }

}
