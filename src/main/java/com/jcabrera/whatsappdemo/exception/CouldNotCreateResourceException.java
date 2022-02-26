package com.jcabrera.whatsappdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CouldNotCreateResourceException extends RuntimeException{
  public CouldNotCreateResourceException(String message) {
    super(message);
  }
}
