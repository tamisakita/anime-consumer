package com.mangaproject.animeconsumer.application.listener;

import com.mangaproject.animeconsumer.application.presentation.representation.AnimeResponseRepresentation;
import com.mangaproject.animeconsumer.domain.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class RabbitMqListener {
    private final RabbitTemplate rabbitTemplate;

    private static final String ANIME_RETURN_MESSAGE = "Message received!";
    private static final String ANIME_EXCHANGE = "amq.direct";

    public RabbitMqListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveSavedAnime(@Payload String message) {
        try {
            AnimeResponseRepresentation anime = JsonUtil.fromJson(message, AnimeResponseRepresentation.class);
            log.info("The anime was saved: {}", anime);
            rabbitTemplate.convertAndSend(ANIME_EXCHANGE, "anime-out-key", ANIME_RETURN_MESSAGE);
        } catch (Throwable e) {
            log.error("Unable to save anime.", e);
        }
    }

//    @RabbitListener(queues = "${spring.rabbitmq.queue.list}")
//    public void receiveListAnime(@Payload String message) {
//        try {
//            AnimeResponseRepresentation[] animes = JsonUtil.fromJson(message, AnimeResponseRepresentation[].class);
//            var animeList = Arrays.asList(animes);
//            log.info("All animes were received: {}", animeList);
//            rabbitTemplate.convertAndSend(ANIME_EXCHANGE, "anime-out-key", ANIME_RETURN_MESSAGE);
//        } catch (Throwable e) {
//            log.error("Unable to find anime list", e);
//        }
//    }
}
