package org.chapeullah.infrastructure.kafka;

import org.chapeullah.UserRegistered;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EventsProducer {

    private final KafkaTemplate<String, UserRegistered> kafkaRegisterTemplate;

    private final String AUTH_EVENTS_TOPIC = "auth.events";

    EventsProducer(KafkaTemplate<String, UserRegistered> kafkaTemplate) {
        this.kafkaRegisterTemplate = kafkaTemplate;
    }

    public void userRegistered(Integer userId, Instant registerDate) {
        UserRegistered userRegistered = new UserRegistered(userId, registerDate);
        kafkaRegisterTemplate.send(AUTH_EVENTS_TOPIC, String.valueOf(userId), userRegistered);
    }

}
