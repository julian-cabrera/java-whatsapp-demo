package com.jcabrera.whatsappdemo.service;

import com.jcabrera.whatsappdemo.exception.CouldNotCreateResourceException;
import com.jcabrera.whatsappdemo.model.Message;
import com.jcabrera.whatsappdemo.model.Webhook;

public interface WebhookService {

  public Message receiveFromChatAPI(Webhook request) throws CouldNotCreateResourceException;

  public Message sendToChatAPI(Message message) throws CouldNotCreateResourceException;
}
