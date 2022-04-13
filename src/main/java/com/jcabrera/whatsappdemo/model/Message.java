package com.jcabrera.whatsappdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonIgnoreProperties(ignoreUnknown = true, value = "chat")
@Schema(description = "Message model from ChatAPI", requiredProperties = {"id", "body"})
public class Message {
  @Id
  @Column(length = 75)
  private String id;

  @Column(nullable = false)
  private String body;

  @Column(length = 8)
  private String type;

  @Column(length = 50)
  private String senderName;

  @Column(length = 50)
  private String author;

  @Column(length = 70)
  private String chatId;

  @Column(length = 50)
  private String mentionedPhones;

  @Column(length = 50)
  private String quotedMsgId;

  @Column(length = 160)
  private String quotedMsgBody;

  
  private String filename;
  private String caption;
  private String audio;
  
  private Integer time; //Unix Timestamp
  private Integer phone;
  private Integer messageNumber;
  private Integer queueNumber;
  
  private Boolean fromMe;
  private Boolean sent;
  private Boolean cached;
  
  public void update(Message message) {
    this.id = message.getId();
    this.body = message.getBody();
    this.type = message.getType();
    this.senderName = message.getSenderName();
    this.author = message.getAuthor();
    this.chatId = message.getChatId();
    this.mentionedPhones = message.getMentionedPhones();
    this.quotedMsgId = message.getQuotedMsgId();
    this.quotedMsgBody = message.getQuotedMsgBody();
    this.filename = message.getFilename();
    this.caption = message.getCaption();
    this.audio = message.getAudio();
    this.time = message.getTime();
    this.phone = message.getPhone();
    this.messageNumber = message.getMessageNumber();
    this.queueNumber = message.getQueueNumber();
  }
}
