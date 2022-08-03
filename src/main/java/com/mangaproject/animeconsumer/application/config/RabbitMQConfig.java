package com.mangaproject.animeconsumer.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue animeQueue() {
        return new Queue("anime-project", true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("animes");
    }

    @Bean
    Binding animeBinding(Queue animeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(animeQueue).to(exchange).with("anime-routing-key");
    }

}