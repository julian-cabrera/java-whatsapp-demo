package com.jcabrera.whatsappdemo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "t_wsp_chat")
@JsonIgnoreProperties(ignoreUnknown = true, value = "messages")
@Schema(description = "Chat model from ChatAPI", requiredProperties = {"id"})
public class Chat {

  @Id
  @Column(length = 75)
  private String id;

  @Column(length = 50)
  private String name;

  private String image;

  @Transient
  @JsonInclude
  private Object metadata;

  private Integer last_time;

  @JsonManagedReference
  @OneToMany(targetEntity = Message.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "chatId", referencedColumnName = "id")
  private List<Message> messages;

  public void update(Chat chat) {
    this.id = chat.getId();
    this.name = chat.getName();
    this.image = chat.getImage();
    this.metadata = chat.getMetadata();
    this.last_time = chat.getLast_time();
  }
}
