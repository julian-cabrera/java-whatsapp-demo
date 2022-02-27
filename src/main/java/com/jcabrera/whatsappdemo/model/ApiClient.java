package com.jcabrera.whatsappdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ChatAPI Client static variables")
public class ApiClient {
	
	/** ChatAPI docs: https://app.chat-api.com/docs **/

  static public final String basePath = "basePath.com/";
  static public final String apiKey = "apiKey";
	static public final String webHook = "path.com/hook";
	static public final String instanceId = "/instanceId";
	static public final String token = "token0000";
}
