package com.jcabrera.whatsappdemo.service.impl;


import com.jcabrera.whatsappdemo.exception.CouldNotCreateResourceException;
import com.jcabrera.whatsappdemo.model.ApiClient;
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
  
  String url;

  @Override
  public Message receiveFromChatAPI(WebhookPayload request) throws CouldNotCreateResourceException {
    try {
      if (request.getMessages() != null && !request.getMessages().isEmpty()) {
        if (chatService.getByChatId(request.getMessages().get(0).getChatId()) != null) {
          //call chatapi, get dialog, save dialog, then save msg
          restTemplate = new RestTemplate();
          //url = "https://api.chat-api.com/instance349303/dialog?token=540odw93019cdm9k&chatId=5493434620007%40c.us";
          //restTemplate.getForEntity(url, responseType, uriVariables)
        }
        return messageService.save(request.getMessages().get(0));
      }
    } catch (CouldNotCreateResourceException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Message sendToChatAPI(Message message) throws CouldNotCreateResourceException {
      
    restTemplate = new RestTemplate();
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

    ApiClient client = clientService.get(Long.valueOf(1));
    url = client.getBasePath() + client.getInstanceId() + messageType + "token=" + client.getToken();
    
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    HttpEntity<?> entity = new HttpEntity<>(message, headers);

    //ResponseEntity<Message> response = 
    restTemplate.exchange(url, HttpMethod.POST, entity, Message.class);

    System.out.println("sendToChatAPI(Request:" + message + " )");
    return messageService.save(message);
  }

}
