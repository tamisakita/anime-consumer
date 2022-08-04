package com.mangaproject.animeconsumer.application.listener;

import com.mangaproject.animeconsumer.application.presentation.representation.AnimeResponseRepresentation;
import com.mangaproject.animeconsumer.domain.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class RabbitMqListener {

    @RabbitListener(queues = {"${queue.name}"})
    public void receiveSavedAnime(@Payload String message) {
        try {
            System.out.println("The anime has been saved " + message);
        } catch (Throwable e) {
            log.error("Deu erro", e);
        }
    }

    @RabbitListener(queues ="anime.list")
    public void receiveListAnime(@Payload String message) {
        try {
            AnimeResponseRepresentation[] animes = JsonUtil.fromJson(message, AnimeResponseRepresentation[].class);
            var animeList = Arrays.asList(animes);
            System.out.println("All animes has been received " + animeList);
        } catch (Throwable e) {
            log.error("Deu erro", e);
        }
    }
}
