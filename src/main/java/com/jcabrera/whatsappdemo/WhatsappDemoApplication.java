package com.jcabrera.whatsappdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rest Api Demo", version = "1.0", description = "ChatAPI and GoogleDrive API integration demo.", 
contact = @Contact(email = "julian.e.cabrera@hotmail.com", name = "Julián Cabrera", url = "https://github.com/julian-cabrera")))
public class WhatsappDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WhatsappDemoApplication.class, args);
	}
}
