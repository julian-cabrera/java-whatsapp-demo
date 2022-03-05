package com.jcabrera.whatsappdemo.service;

import com.jcabrera.whatsappdemo.exception.CouldNotCreateResourceException;
import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.model.WebhookPayload;

public interface WebhookService {

  public Message receiveFromChatAPI(WebhookPayload request) throws CouldNotCreateResourceException;

  public Message sendToChatAPI(Message message) throws CouldNotCreateResourceException;
}
