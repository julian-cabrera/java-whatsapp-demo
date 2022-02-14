package com.jcabrera.whatsappdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Message model from ChatAPI", requiredProperties = {"id", "body"})
public class Message {
  private String id;
  private Boolean sent;
  private String body;
  private String type;
  private String senderName;
  private Boolean fromMe;
  private String author;
  private Integer time; //Unix Timestamp
  private String chatId;
  private String mentionedPhones;
  private String quotedMsgId;
  private String quotedMsgBody;
  private Integer phone;
  private Integer messageNumber;
  private Integer queueNumber;
  private String filename;
  private String caption;
  private Boolean cached;
  private String audio;


}
