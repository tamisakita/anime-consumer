package com.mangaproject.animeconsumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class AnimeConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeConsumerApplication.class, args);
	}

}
