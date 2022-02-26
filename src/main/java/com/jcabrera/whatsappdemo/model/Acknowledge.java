package com.jcabrera.whatsappdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Message status")
public class Acknowledge {
  private String id;
  private Integer queueNumber;
  private String chatId;
  private String status;
}
