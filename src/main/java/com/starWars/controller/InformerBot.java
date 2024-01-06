package com.starWars.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.starWars.*"})
public class InformerBot {

	public static void main(String[] args) {
		SpringApplication.run(InformerBot.class, args);
	}

}
