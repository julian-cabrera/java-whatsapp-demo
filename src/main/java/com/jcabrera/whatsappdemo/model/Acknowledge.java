package com.jcabrera.whatsappdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;

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
