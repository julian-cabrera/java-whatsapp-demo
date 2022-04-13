package com.jcabrera.whatsappdemo.service.impl;

import com.jcabrera.whatsappdemo.exception.CouldNotCreateResourceException;
import com.jcabrera.whatsappdemo.model.ApiClient;
import com.jcabrera.whatsappdemo.model.Chat;
import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.model.WebhookPayload;
import com.jcabrera.whatsappdemo.service.ApiClientService;
import com.jcabrera.whatsappdemo.service.ChatService;
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
  ApiClientService clientService;
  
  @Autowired
  MessageService messageService;

  @Autowired
  ChatService chatService;

  RestTemplate restTemplate;
  HttpHeaders headers;
  HttpEntity<?> entity;
  
  String url;

  @Override
  public Message receiveFromChatAPI(WebhookPayload request) throws CouldNotCreateResourceException {
    try {
      if (request.getMessages() != null && !request.getMessages().isEmpty()) {

        Message message = request.getMessages().get(0);

        if (chatService.getByChatId(message.getChatId()) == null) {
          
          restTemplate = new RestTemplate();
          headers = new HttpHeaders();
          headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
          entity = new HttpEntity<>(headers);

          
          ApiClient client = clientService.findByInstanceNumber(349303); //Hardcoded for now.
          url = client.getBasePath() + "/instance" + client.getInstanceId() + "/dialog?token=" + client.getToken() + "&chatId=" + message.getChatId();
          
          ResponseEntity<Chat> response = restTemplate.exchange(url, HttpMethod.GET, entity, Chat.class);
          Chat newChat = response.getBody();

          if (newChat.getId() == null) {
            return null;
          }
          System.out.println("Saved chat: " + newChat);
          System.out.println("Saved message: " + message);

          chatService.save(newChat);
          return messageService.save(message);
        } else {
          System.out.println("Saved message: " + message);
          return messageService.save(message);
        }
      }
    } catch (CouldNotCreateResourceException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Message sendToChatAPI(Message message) throws CouldNotCreateResourceException {
      
    restTemplate = new RestTemplate();
    headers = new HttpHeaders();
    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    entity = new HttpEntity<>(message, headers);
    String messageType = "";

    if (message.getBody() != null && message.getBody().length() > 10000) {
      throw new CouldNotCreateResourceException("Message is too long, can't excede 10k characters.");
    }

    if (!message.getAudio().equals(null) && !message.getAudio().equals("")) {
      //TODO Google Drive API not yet implemented.
      // String idAudio = fileService.uploadFromUrl(message.getAudio(), "ogg");
      // message.setAudio("https://drive.google.com/uc?export=view&id=" + idAudio);
      messageType = "/sendPTT?";
    }

    if (message.getType().equals("chat") || message.getType().equals(null)) {
      messageType = "/sendMessage?";
    } else {
      messageType = "/sendFile?";
      //fileService.uploadFromUrl(message.getBody(), )
    }

    ApiClient client = clientService.findByInstanceNumber(349303); //Hardcoded for now.
    url = client.getBasePath() + "/instance" + client.getInstanceId() + messageType + "token=" + client.getToken();
    
    ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.POST, entity, Message.class);
    Message newMessage = response.getBody();

    if (newMessage.getId() == null) { return null; }

    System.out.println("***** Saved chat: " + newMessage);
    System.out.println("***** Saved message: " + newMessage);

    System.out.println("sendToChatAPI(Request:" + message + " )");
    return messageService.save(response.getBody());
  }

}
