package com.jcabrera.whatsappdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@Builder
@Entity
@Table(name = "t_wsp_client")
@Schema(description = "ChatAPI Client variables")
public class ApiClient {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId;
	
	@Column(length = 25)
	private String basePath;
  
	@Column(length = 30)
	private String apiKey;
	
	private String webHook;
	
	@Column(unique = true)
	private Integer instanceId;
	
	@Column(length = 20, unique = true)
	private String token;

	public void update(ApiClient client) {
		this.basePath = client.getBasePath();
		this.apiKey = client.getApiKey();
		this.webHook = client.getWebHook();
		this.instanceId = client.getInstanceId();
		this.token = client.getToken();
	}
}
