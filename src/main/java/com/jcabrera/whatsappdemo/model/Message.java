package com.jcabrera.whatsappdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Builder
@Entity
@Table(name = "t_wsp_message")
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Message model from ChatAPI", requiredProperties = {"id", "body"})
public class Message {
  @Id
  private String id;
  private String body;
  private String type;
  private String senderName;
  private String author;
  private String chatId;
  private String mentionedPhones;
  private String quotedMsgId;
  private String quotedMsgBody;
  private String filename;
  private String caption;
  private String audio;

  private Integer time; //Unix Timestamp
  private Integer phone;
  private Integer messageNumber;
  private Integer queueNumber;

  private Boolean sent;
  private Boolean fromMe;
  private Boolean cached;
  
  public void update(Message message) {
    System.out.println("Message update()");
  }
}
