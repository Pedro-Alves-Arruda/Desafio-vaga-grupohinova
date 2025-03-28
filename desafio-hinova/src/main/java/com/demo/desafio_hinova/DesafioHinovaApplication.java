package com.demo.desafio_hinova;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableCaching
public class DesafioHinovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioHinovaApplication.class, args);
	}
}


